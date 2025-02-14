package com.example.gestion_juegos_kotlin.providers

import com.example.gestion_juegos_kotlin.models.Game
import com.example.gestion_juegos_kotlin.services.GameService

object GamesProvider {
    private lateinit var _games: List<Game>
    private lateinit var _filteredGames: List<Game>
    lateinit var selectedGame: Game

    val filteredGames: List<Game>
        get() {
            return _filteredGames
        }

    suspend fun initialize() {
        if (!this::_games.isInitialized) {
            _games = GameService.getGames()
            _filteredGames = _games
        }
    }

    fun filterGamesByTitle(title: String = "")  {
        if (title == "") {
            _filteredGames = _games
            return
        }

        _filteredGames = _games.filter { game -> game.title.contains(title, true) }
    }
}