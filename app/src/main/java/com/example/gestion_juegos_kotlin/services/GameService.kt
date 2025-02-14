package com.example.gestion_juegos_kotlin.services

import android.util.Log
import com.example.gestion_juegos_kotlin.models.Game

object GameService {
    suspend fun getGames(): List<Game> {
        val response = RetrofitClient.instance.getGames()

        if (response.isSuccessful) {
            return response.body()!!.map { Game.fromResponse(it) }
        }

        Log.e("APIGames", "Error: code ${response.code()}")
        return listOf()
    }
}