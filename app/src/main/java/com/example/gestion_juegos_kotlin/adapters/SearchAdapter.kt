package com.example.gestion_juegos_kotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gestion_juegos_kotlin.R
import com.example.gestion_juegos_kotlin.databinding.SearchElementBinding
import com.example.gestion_juegos_kotlin.models.Game

class SearchAdapter(private var games: List<Game>, val onClickOpenDetails: (Game) -> Unit): RecyclerView.Adapter<SearchAdapter.GameHolder>() {
    class GameHolder(val view: View): RecyclerView.ViewHolder(view) {
        private val binding = SearchElementBinding.bind(view)

        fun render(game: Game) {
            // TODO asiganar valores
            binding.gameImg.setImageBitmap(game.image)
            binding.titleTxt.text = game.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
        return GameHolder(LayoutInflater.from(parent.context).inflate(R.layout.search_element, parent, false))
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