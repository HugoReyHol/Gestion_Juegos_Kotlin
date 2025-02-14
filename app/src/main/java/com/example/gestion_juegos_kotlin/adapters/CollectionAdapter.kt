package com.example.gestion_juegos_kotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gestion_juegos_kotlin.R
import com.example.gestion_juegos_kotlin.databinding.CollectionElementBinding
import com.example.gestion_juegos_kotlin.models.Game

class CollectionAdapter(private var games: List<Game>, val onClickOpenDetails: (Game) -> Unit): RecyclerView.Adapter<CollectionAdapter.GameHolder>() {
    class GameHolder(val view: View): RecyclerView.ViewHolder(view) {
        private val binding = CollectionElementBinding.bind(view)

        fun render(game: Game) {
            binding.gameImg.setImageBitmap(game.image)
            binding.gameTxt.text = game.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
        return GameHolder(LayoutInflater.from(parent.context).inflate(R.layout.collection_element ,parent, false))
    }

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: GameHolder, position: Int) {
        holder.render(games[position])
        holder.view.setOnClickListener {
            onClickOpenDetails(games[position])
        }
    }

    fun setNewGames(newGames: List<Game>) {
        games = newGames
        notifyDataSetChanged()
    }
}