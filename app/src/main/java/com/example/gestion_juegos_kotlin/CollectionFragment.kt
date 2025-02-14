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
import com.example.gestion_juegos_kotlin.providers.HomeProvider
import kotlinx.coroutines.launch

class CollectionFragment : Fragment() {
    private lateinit var binding: FragmentCollectionBinding
    private lateinit var adapter: CollectionAdapter

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
    }

    private fun inicializeRecycler() {
        binding.collectioRecycler.layoutManager = GridLayoutManager(context, 3)
        adapter = CollectionAdapter(listOf()) {onClickOpenDetails(it)}
        binding.collectioRecycler.adapter = adapter
        initializeProviders()
    }

    private fun initializeProviders() {
        lifecycleScope.launch {
            HomeProvider.initialize()
            adapter.setNewGames(HomeProvider.homeGames)
        }
    }

    private fun onClickOpenDetails(game: Game) {
        TODO("Guardar game en provider")
        startActivity(
            Intent(context, DetailsActivity::class.java)
        )
    }
}