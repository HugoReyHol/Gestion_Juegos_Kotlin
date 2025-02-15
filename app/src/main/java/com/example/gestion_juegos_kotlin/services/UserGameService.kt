package com.example.gestion_juegos_kotlin.services

import android.util.Log
import com.example.gestion_juegos_kotlin.models.UserGame
import com.example.gestion_juegos_kotlin.models.UserGameInsert
import com.example.gestion_juegos_kotlin.models.UserGameUpdate
import com.example.gestion_juegos_kotlin.providers.UserProvider

object UserGameService {
    suspend fun getUserGames(): List<UserGame>{
        val token = UserProvider.user!!.token

        val response = RetrofitClient.instance.getUserGames(token)

        if (response.isSuccessful) {
            return response.body()!!.map { UserGame.fromResponse(it) }

        } else {
            Log.e("APIUserGames", "Error al obtener juegos de usuario")
        }

        return listOf()
    }

    suspend fun insertUserGame(userGame: UserGame): Boolean {
        val token = UserProvider.user!!.token
        val body = UserGameInsert.fromUserGame(userGame)

        Log.i("APIUserGames", "$body")

        val response = RetrofitClient.instance.insertUserGame(token, body)

        if (response.isSuccessful) {
            return true

        } else {
            Log.e("APIUserGames", "Error al insertar juego de usuario")
        }

        return false
    }

    suspend fun updateUserGame(userGame: UserGame, update: UserGameUpdate) {
        val token = UserProvider.user!!.token
        val id = userGame.idGame

        val response = RetrofitClient.instance.updateUserGame(id, token, update)

        if (response.isSuccessful) {
            // TODO actualizar interfaz y userGame

        } else {
            Log.e("APIUserGames", "Error al actualizar juego de usuario")
        }
    }

    suspend fun deleteUserGame(userGame: UserGame): Boolean {
        val token = UserProvider.user!!.token
        val id = userGame.idGame

        val response = RetrofitClient.instance.deleteUserGame(id, token)

        if (response.isSuccessful) {
            return  true

        } else {
            Log.e("APIUserGames", "Error al borrar juego de usuario")
        }

        return false
    }
}