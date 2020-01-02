package com.camilo.kofu.kofudemo.user

import org.springframework.web.reactive.function.server.coRouter

fun userRouter(userHandler: UserHandler) = coRouter {
    GET("/api/user",  userHandler::listAll)
}
