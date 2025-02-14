package com.example.gestion_juegos_kotlin.providers

import com.example.gestion_juegos_kotlin.models.UserGame

object UserGameProvider {
    private var _userGames: List<UserGame> = listOf()

    var userGames: List<UserGame>
        get() {
            return _userGames
        }
        set(value) {
            _userGames = value
        }
}