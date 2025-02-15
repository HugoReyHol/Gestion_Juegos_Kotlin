package com.example.gestion_juegos_kotlin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gestion_juegos_kotlin.adapters.CollectionAdapter
import com.example.gestion_juegos_kotlin.databinding.FragmentCollectionBinding
import com.example.gestion_juegos_kotlin.models.Game
import com.example.gestion_juegos_kotlin.providers.GamesProvider
import com.example.gestion_juegos_kotlin.providers.HomeProvider
import kotlinx.coroutines.launch

class CollectionFragment : Fragment() {
    companion object {
        var adapter: CollectionAdapter? = null
    }

    private lateinit var binding: FragmentCollectionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCollectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inicializeRecycler()
        initializeProviders()
    }

    private fun inicializeRecycler() {
        binding.collectioRecycler.layoutManager = GridLayoutManager(context, 3)
        adapter = CollectionAdapter(listOf()) {onClickOpenDetails(it)}
        binding.collectioRecycler.adapter = adapter
    }

    private fun initializeProviders() {
        lifecycleScope.launch {
            HomeProvider.initialize()
            adapter?.setNewGames(HomeProvider.homeGames)
        }
    }

    private fun onClickOpenDetails(game: Game) {
        GamesProvider.selectedGame = game
        startActivity(
            Intent(context, DetailsActivity::class.java)
        )
    }
}