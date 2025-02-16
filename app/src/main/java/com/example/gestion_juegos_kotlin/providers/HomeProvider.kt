package com.example.gestion_juegos_kotlin.providers

import com.example.gestion_juegos_kotlin.CollectionFragment
import com.example.gestion_juegos_kotlin.models.Game
import com.example.gestion_juegos_kotlin.models.UserGame
import com.example.gestion_juegos_kotlin.models.UserGame.Companion.GameStates

object HomeProvider {
    private lateinit var _homeGames: ArrayList<Game>

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

    fun filterHomeGames(title: String?, state: GameStates?) {
        GamesProvider.filterGamesByTitle(title)
        UserGamesProvider.filterUserGamesByState(state)

        val games = GamesProvider.filteredGames
        val userGames = UserGamesProvider.filteredUserGames

        _homeGames = games.filter { g -> userGames.any { ug -> g.idGame == ug.idGame } } as ArrayList<Game>
    }

    fun deleteUserGameFromFilter(userGame: UserGame) {
        val index = _homeGames.indexOfFirst { g -> g.idGame == userGame.idGame }

        if (index == -1) return

        _homeGames.removeAt(index)
        CollectionFragment.adapter?.notifyItemRemoved(index)
    }
}