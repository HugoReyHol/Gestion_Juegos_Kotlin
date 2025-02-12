package com.example.gestion_juegos_kotlin.models

data class User(
    var idUser: Int = -1,
    var username: String,
    var password: String,
    var token: String = ""
)