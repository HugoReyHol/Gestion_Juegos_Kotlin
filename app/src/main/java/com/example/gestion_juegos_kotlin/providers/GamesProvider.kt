package com.example.gestion_juegos_kotlin.providers

import com.example.gestion_juegos_kotlin.models.Game
import com.example.gestion_juegos_kotlin.services.GameService

object GamesProvider {
    private var _filteredGames: List<Game> = GameService.games

    val filteredGames: List<Game>
        get() {
            return _filteredGames
        }

    fun filterGamesByTitle(title: String = "") {
        if (title == "") {
            _filteredGames = GameService.games
            return
        }

        _filteredGames = _filteredGames.filter { game -> game.title.contains(title, true) }
    }
}