package com.camilo.kofu.kofudemo

import org.springframework.boot.WebApplicationType
import org.springframework.fu.kofu.application

val app = application(WebApplicationType.REACTIVE) {
    configurationProperties<Property>(prefix = "example")
    enable(dataConfiguration)
    enable(webConfiguration)
}
fun main(args: Array<String>) {
    app.run(args)
}
