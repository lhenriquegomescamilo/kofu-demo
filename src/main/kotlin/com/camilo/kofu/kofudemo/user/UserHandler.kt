package com.camilo.kofu.kofudemo.user

import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyAndAwait

class UserHandler(
    private val repository: UserRepostiory
) {
    suspend fun listAll(request: ServerRequest) = ok()
        .contentType(MediaType.APPLICATION_JSON)
        .bodyAndAwait(repository.findAll())
}
