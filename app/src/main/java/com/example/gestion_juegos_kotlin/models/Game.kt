package com.example.gestion_juegos_kotlin.models

import android.graphics.Bitmap

data class Game(
    val idGame: Int,
    val title: String,
    val description: String,
    val image: Bitmap,
    val details: String,
    val releases: String,
)