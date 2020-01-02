package com.camilo.kofu.kofudemo.user

import org.springframework.data.r2dbc.core.*

class UserRepostiory(private val client: DatabaseClient) {
    suspend fun insertFakeData() {
        client.execute("""
            CREATE TABLE IF NOT EXISTS users (
                        login varchar PRIMARY KEY, 
                        firstname varchar,
                        lastname varchar, 
                        password varchar
            );
            """).await()

        deleteAll()

        save(UserModel(
            login = "camilo",
            firstname = "Luis",
            lastname = "Henrique",
            password = "123456"
        ))

    }

    suspend fun save(user: UserModel) = client.insert().into<UserModel>().table("users").using(user).await()
    suspend fun deleteAll() = client.execute("DELETE FROM users").await()
    fun findAll() = client.select().from("users").asType<UserModel>().fetch().flow()

    suspend fun findByLogin(login: String) = client.execute("SELECT * FROM users WHERE login = :login")
        .bind("login", login)
        .asType<UserModel>().fetch()
        .awaitOne()


}
