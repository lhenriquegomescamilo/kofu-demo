package com.camilo.kofu.kofudemo

import com.camilo.kofu.kofudemo.user.UserHandler
import com.camilo.kofu.kofudemo.user.UserRepostiory
import com.camilo.kofu.kofudemo.user.userRouter
import kotlinx.coroutines.runBlocking
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.fu.kofu.configuration
import org.springframework.fu.kofu.r2dbc.r2dbcH2
import org.springframework.fu.kofu.webflux.webFlux

val dataConfiguration = configuration {
    beans {
        bean<UserRepostiory>()
    }

    /*
     * Quando a aplicação inicializar ela irá lançar esse evento. Seria o equivalente ao OnInit
     */
    listener<ApplicationReadyEvent> {
        runBlocking {
            ref<UserRepostiory>().insertFakeData()
        }
    }
    r2dbcH2()
}


val webConfiguration = configuration {
    beans {
        bean<UserHandler>()
        bean(::userRouter)
    }

    webFlux {
        port = if (profiles.contains("test")) 8181 else 8080
        codecs {
            string()
            jackson()
        }
    }
}
