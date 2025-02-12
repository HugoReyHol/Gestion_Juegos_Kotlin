package com.example.gestion_juegos_kotlin.services

import android.util.Log
import com.example.gestion_juegos_kotlin.models.UserGame
import com.example.gestion_juegos_kotlin.models.UserGameInsert
import com.example.gestion_juegos_kotlin.models.UserGameResponse
import com.example.gestion_juegos_kotlin.models.UserGameUpdate
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object UserGameService {
    private lateinit var _userGames: List<UserGame>

    val userGames: List<UserGame>
        get() {
            if (!this::_userGames.isInitialized) {
                getUserGames()
            }

            return _userGames
        }

        private fun getUserGames() {
            val token = UserService.user!!.token

            RetrofitClient.instance.getUserGames(token).enqueue(object : Callback<List<UserGameResponse>> {
                override fun onResponse(call: Call<List<UserGameResponse>>,response: Response<List<UserGameResponse>>) {
                    if (response.isSuccessful) {
                        _userGames = response.body()!!.map { UserGame.fromResponse(it) }

                    } else {
                        Log.e("APIUserGames", "Error al obtener juegos de usuario")
                    }
                }

                override fun onFailure(call: Call<List<UserGameResponse>>, t: Throwable) {
                    Log.e("APIUserGames", t.message.toString())
                }
            })
        }

    fun insertUserGame(userGame: UserGame) {
        val token = UserService.user!!.token
        val body = UserGameInsert.fromUserGame(userGame)

        RetrofitClient.instance.insertUserGame(token, body).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // TODO actualizar interfaz

                } else {
                    Log.e("APIUserGames", "Error al insertar juego de usuario")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("APIUserGames", t.message.toString())
            }
        })
    }

    fun updateUserGame(userGame: UserGame, update: UserGameUpdate) {
        val token = UserService.user!!.token
        val id = userGame.idGame

        RetrofitClient.instance.updateUserGame(id, token, update).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // TODO actualizar interfaz y userGame

                } else {
                    Log.e("APIUserGames", "Error al actualizar juego de usuario")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("APIUserGames", t.message.toString())
            }
        })
    }

    fun deleteUserGame(userGame: UserGame) {
        val token = UserService.user!!.token
        val id = userGame.idGame

        RetrofitClient.instance.deleteUserGame(id, token).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    // TODO actualizar interfaz

                } else {
                    Log.e("APIUserGames", "Error al borrar juego de usuario")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("APIUserGames", t.message.toString())
            }
        })
    }
}