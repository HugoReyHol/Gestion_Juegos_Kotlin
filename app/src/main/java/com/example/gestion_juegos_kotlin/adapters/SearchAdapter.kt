package com.example.gestion_juegos_kotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gestion_juegos_kotlin.R
import com.example.gestion_juegos_kotlin.models.Game

class SearchAdapter(private var games: List<Game>, val onClickOpenDetails: (Game) -> Unit): RecyclerView.Adapter<SearchAdapter.GameHolder>() {
    class GameHolder(val view: View): RecyclerView.ViewHolder(view) {
        // TODO añadir elemento horizontal

        fun render(game: Game) {
            // TODO asiganar valores
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
        // TODO añadir elemento horizontal
        return GameHolder(LayoutInflater.from(parent.context).inflate(R.layout , parent, false))
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