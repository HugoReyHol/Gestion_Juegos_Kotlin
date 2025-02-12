package com.example.gestion_juegos_kotlin.services

import android.util.Log
import com.example.gestion_juegos_kotlin.models.Game
import com.example.gestion_juegos_kotlin.models.GameResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GameService {
    lateinit var _games: List<Game>

    val games: List<Game>
        get() {
            if (!this::_games.isInitialized) {
                getGames()
            }

            return _games
        }

    private fun getGames() {
        RetrofitClient.instance.getGames().enqueue(object : Callback<List<GameResponse>> {
            override fun onResponse(call: Call<List<GameResponse>>, response: Response<List<GameResponse>>) {
                if (response.isSuccessful) {
                    val gameResponse = response.body()
                    _games = gameResponse!!.map { Game.fromResponse(it) }

                } else {
                    Log.e("APIGames", "Error al obtener juegos")
                }
            }

            override fun onFailure(call: Call<List<GameResponse>>, t: Throwable) {
                Log.e("APIGames", t.message.toString())
            }
        })
    }
}