package com.example.gestion_juegos_kotlin.models

data class UserGame(
    val idGame: Int,
    val idUser: Int,
    var score: Int?,
    var timePlayed: Int,
    var gameState: GameStates,
) {
    enum class GameStates {playing, completed, on_hold, dropped, plan_to_play}


}
