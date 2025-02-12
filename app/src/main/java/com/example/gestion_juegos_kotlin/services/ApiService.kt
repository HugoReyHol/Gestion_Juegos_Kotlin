package com.example.gestion_juegos_kotlin.services

import com.example.gestion_juegos_kotlin.models.GameResponse
import com.example.gestion_juegos_kotlin.models.User
import com.example.gestion_juegos_kotlin.models.UserGameInsert
import com.example.gestion_juegos_kotlin.models.UserGameResponse
import com.example.gestion_juegos_kotlin.models.UserGameUpdate
import com.example.gestion_juegos_kotlin.models.UserRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    // Métodos de user
    @POST("/user/login")
    fun loginUser(@Body request: UserRequest): Call<User>

    @POST("/user/insert")
    fun registerUser(@Body request: UserRequest): Call<User>

    // Métodos de game
    @GET("/game")
    fun getGames(): Call<List<GameResponse>>

    // Métidos de usergame
    @GET("/user_game")
    fun getUserGames(@Header("Authorization") token: String): Call<List<UserGameResponse>>

    @POST("/user_game")
    fun insertUserGame(
        @Header("Authorization") token: String,
        @Body body: UserGameInsert
    ): Call<Void>

    @PATCH("/{idGame}")
    fun updateUserGame(
        @Path("idGame") idGame: Int,
        @Header("Authorization") token: String,
        @Body body: UserGameUpdate
    ): Call<Void>

    @DELETE("/{idGame}")
    fun deleteUserGame(
        @Path("idGame") idGame: Int,
        @Header("Authorization") token: String,
    ): Call<Void>
}