package com.example.gestion_juegos_kotlin.models

data class UserGame(
    val idGame: Int,
    val idUser: Int,
    var score: Int? = null,
    var timePlayed: Int = 0,
    var gameState: GameStates = GameStates.plan_to_play,
) {
    companion object {
        enum class GameStates(val string: String) {
            playing("JUGANDO"),
            completed("COMPLETADO"),
            on_hold("EN ESPERA"),
            dropped("ABANDONADO"),
            plan_to_play("PENDIENTE");
        }

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
    var lastChange: Long
)

data class UserGameInsert(
    val idGame: Int,
    val score: Int?,
    val timePlayed: Int,
    val gameState: String,
    val lastChange: Long = System.currentTimeMillis()
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
    var lastChange: Long = System.currentTimeMillis()
)