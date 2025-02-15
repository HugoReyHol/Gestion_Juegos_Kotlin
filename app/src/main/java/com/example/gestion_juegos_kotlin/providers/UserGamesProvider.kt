package com.example.gestion_juegos_kotlin.providers

import com.example.gestion_juegos_kotlin.models.UserGame
import com.example.gestion_juegos_kotlin.services.UserGameService
import com.example.gestion_juegos_kotlin.models.UserGame.Companion.GameStates

object UserGamesProvider {
    private lateinit var _userGames: List<UserGame>
    private lateinit var _filteredUserGames: List<UserGame>

    val userGames: List<UserGame>
        get() {
            return _userGames
        }

    val filteredUserGames: List<UserGame>
        get() {
            return _filteredUserGames
        }

    suspend fun initialize() {
        if (!this::_userGames.isInitialized) {
            _userGames = UserGameService.getUserGames()
        }
        _filteredUserGames = _userGames.filter { ug -> ug.gameState == GameStates.playing }
    }

    fun filterUserGamesByState(state: GameStates?) {
        if (state == null) {
            _filteredUserGames = _userGames
            return
        }

        _filteredUserGames = _userGames.filter { ug -> ug.gameState == state }
    }
}