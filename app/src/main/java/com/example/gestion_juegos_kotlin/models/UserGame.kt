package com.example.gestion_juegos_kotlin.models

import java.time.Instant

data class UserGame(
    val idGame: Int,
    val idUser: Int,
    var score: Int?,
    var timePlayed: Int,
    var gameState: GameStates,
) {
    companion object {
        enum class GameStates {playing, completed, on_hold, dropped, plan_to_play}

        fun fromResponse(response: UserGameResponse): UserGame {
            val state : GameStates = GameStates.valueOf(response.gameState)

            return UserGame(
                idGame = response.idGame,
                idUser = response.idUser,
                score = response.score,
                timePlayed = response.timePlayed,
                gameState = state
            )
        }
    }
}

data class UserGameResponse(
    val idGame: Int,
    val idUser: Int,
    var score: Int?,
    var timePlayed: Int,
    var gameState: String,
    var lastChange: Int
)

data class UserGameInsert(
    val idGame: Int,
    var score: Int?,
    var timePlayed: Int,
    var gameState: String,
    var lastChange: Instant = Instant.now()
) {
    companion object {
        fun fromUserGame(userGame: UserGame): UserGameInsert {
            return UserGameInsert(
                idGame = userGame.idGame,
                score = userGame.score,
                timePlayed = userGame.timePlayed,
                gameState = userGame.gameState.name
            )
        }
    }
}

data class UserGameUpdate(
    var score: Int? = null,
    var timePlayed: Int? = null,
    var gameState: String? = null,
    var lastChange: Instant = Instant.now()
)