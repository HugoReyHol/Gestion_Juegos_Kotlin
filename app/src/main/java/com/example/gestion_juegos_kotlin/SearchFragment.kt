package com.example.gestion_juegos_kotlin

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gestion_juegos_kotlin.adapters.SearchAdapter
import com.example.gestion_juegos_kotlin.databinding.FragmentGamesBinding
import com.example.gestion_juegos_kotlin.models.Game
import com.example.gestion_juegos_kotlin.providers.GamesProvider
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentGamesBinding
    private lateinit var adapter: SearchAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGamesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inicializeRecycler()
        initializeProvider()
    }

    private fun inicializeRecycler() {
        binding.gamesRecycler.layoutManager = LinearLayoutManager(context)
        adapter = SearchAdapter(listOf()){onClickOpenDetails(it)}
        binding.gamesRecycler.adapter = adapter
    }

    private fun initializeProvider() {
        lifecycleScope.launch {
            GamesProvider.initialize()
            adapter.setNewGames(GamesProvider.filteredGames)
        }
    }

    private fun onClickOpenDetails(game: Game) {
        GamesProvider.selectedGame = game
        startActivity(
            Intent(context, DetailsActivity::class.java)
        )
    }

}