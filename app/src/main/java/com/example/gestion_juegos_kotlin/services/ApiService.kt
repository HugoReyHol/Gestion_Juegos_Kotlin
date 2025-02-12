package com.example.gestion_juegos_kotlin.services

import com.example.gestion_juegos_kotlin.models.GameResponse
import com.example.gestion_juegos_kotlin.models.User
import com.example.gestion_juegos_kotlin.models.UserRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("/user/login")
    fun loginUser(@Body request: UserRequest): Call<User>

    @POST("/user/insert")
    fun registerUser(@Body request: UserRequest): Call<User>

    @GET("/game")
    fun getGames(): Call<List<GameResponse>>
}