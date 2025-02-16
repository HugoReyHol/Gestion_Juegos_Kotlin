package com.example.gestion_juegos_kotlin.providers

import com.example.gestion_juegos_kotlin.CollectionFragment
import com.example.gestion_juegos_kotlin.models.Game
import com.example.gestion_juegos_kotlin.models.UserGame
import com.example.gestion_juegos_kotlin.models.UserGame.Companion.GameStates

object HomeProvider {
    private lateinit var _homeGames: ArrayList<Game>
    var currentState: GameStates? = GameStates.playing

    val homeGames: List<Game>
        get() {
            return _homeGames
        }

    suspend fun initialize() {
        if (!this::_homeGames.isInitialized) {
            GamesProvider.initialize()
            UserGamesProvider.initialize()

        }

        val games = GamesProvider.filteredGames
        val userGames = UserGamesProvider.filteredUserGames

        _homeGames = games.filter { g -> userGames.any { ug -> g.idGame == ug.idGame } } as ArrayList<Game>
    }

    fun filterHomeGames(title: String?) {
        GamesProvider.filterGamesByTitle(title)
        UserGamesProvider.filterUserGamesByState(currentState)

        val games = GamesProvider.filteredGames
        val userGames = UserGamesProvider.filteredUserGames

        _homeGames = games.filter { g -> userGames.any { ug -> g.idGame == ug.idGame } } as ArrayList<Game>
    }

    fun onUserGameStateChanged(userGame: UserGame) {
        if (currentState == userGame.gameState) {
            _homeGames.add(GamesProvider.selectedGame)
            CollectionFragment.adapter?.notifyItemInserted(_homeGames.lastIndex)

        } else {
            deleteSelectedGame()

        }
    }

    fun deleteSelectedGame() {
        val index = _homeGames.indexOf(GamesProvider.selectedGame)
        _homeGames.removeAt(index)
        CollectionFragment.adapter?.notifyItemRemoved(index)
    }
}