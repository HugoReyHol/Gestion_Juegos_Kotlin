package com.example.gestion_juegos_kotlin.models

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

data class Game(
    val idGame: Int,
    val title: String,
    val description: String,
    val image: Bitmap,
    val details: String,
    val releases: String,
) {
    companion object {
        fun fromResponse(response: GameResponse): Game {
            val decodedBytes = Base64.decode(response.image, Base64.DEFAULT)
            val bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.size)
            return Game(
                idGame = response.idGame,
                title = response.title,
                description = response.description,
                image = bitmap,
                details = response.details,
                releases = response.releases
            )
        }
    }
}

data class GameResponse(
    val idGame: Int,
    val title: String,
    val description: String,
    val image: String,
    val details: String,
    val releases: String,
)