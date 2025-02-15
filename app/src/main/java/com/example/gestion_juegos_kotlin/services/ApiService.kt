package com.example.gestion_juegos_kotlin.services

import com.example.gestion_juegos_kotlin.models.GameResponse
import com.example.gestion_juegos_kotlin.models.User
import com.example.gestion_juegos_kotlin.models.UserGameInsert
import com.example.gestion_juegos_kotlin.models.UserGameResponse
import com.example.gestion_juegos_kotlin.models.UserGameUpdate
import com.example.gestion_juegos_kotlin.models.UserRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    // Métodos de user
    @POST("/user/login")
    suspend fun loginUser(@Body request: UserRequest): Response<User>

    @POST("/user/insert")
    suspend fun registerUser(@Body request: UserRequest): Response<User>

    // Métodos de game
    @GET("/game/")
    suspend fun getGames(): Response<List<GameResponse>>

    // Métodos de usergame
    @GET("/user_game/")
    suspend fun getUserGames(@Header("Authorization") token: String): Response<List<UserGameResponse>>

    @POST("/user_game/")
    suspend fun insertUserGame(
        @Header("Authorization") token: String,
        @Body body: UserGameInsert
    ): Response<Void>

    @PATCH("/user_game/{idGame}")
    suspend fun updateUserGame(
        @Path("idGame") idGame: Int,
        @Header("Authorization") token: String,
        @Body body: UserGameUpdate
    ): Response<Void>

    @DELETE("/user_game/{idGame}")
    suspend fun deleteUserGame(
        @Path("idGame") idGame: Int,
        @Header("Authorization") token: String,
    ): Response<Void>
}