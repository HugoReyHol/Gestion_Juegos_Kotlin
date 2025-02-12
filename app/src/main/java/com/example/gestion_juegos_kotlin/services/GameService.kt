package com.example.gestion_juegos_kotlin.services

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.gestion_juegos_kotlin.models.Game
import com.example.gestion_juegos_kotlin.models.GameResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object GameService {
    lateinit var games: List<Game>

    fun getGames(context: Context) {
        RetrofitClient.instance.getGames().enqueue(object : Callback<List<GameResponse>> {
            override fun onResponse(call: Call<List<GameResponse>>, response: Response<List<GameResponse>>) {
                if (response.isSuccessful) {
                    val gameResponse = response.body()
                    games = gameResponse!!.map { Game.fromResponse(it) }

                } else {
                    Toast.makeText(context, "Error al obtener juegos", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<GameResponse>>, t: Throwable) {
                Log.e("APIGames", t.message.toString())
                Toast.makeText(context, "Error de conexión", Toast.LENGTH_SHORT).show()
            }

        })
    }
}