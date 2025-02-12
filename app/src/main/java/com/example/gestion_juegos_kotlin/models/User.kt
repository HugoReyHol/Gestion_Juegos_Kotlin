package com.example.gestion_juegos_kotlin.models

data class User(
    var idUser: Int,
    var username: String,
    var password: String,
    var token: String
)

data class UserRequest(
    val username: String,
    val password: String
)