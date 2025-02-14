package com.example.gestion_juegos_kotlin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gestion_juegos_kotlin.R
import com.example.gestion_juegos_kotlin.models.Game

class CollectionAdapter(private val games: List<Game>, val onClickOpenDetails: (Game) -> Unit): RecyclerView.Adapter<CollectionAdapter.GameHolder>() {
    class GameHolder(val view: View): RecyclerView.ViewHolder(view) {
        // TODO agregar elemento coleccion

        fun render(game: Game) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameHolder {
        TODO("Añadir layout game")
        return GameHolder(LayoutInflater.from(parent.context).inflate(R.layout. ,parent, false))
    }

    override fun getItemCount(): Int = games.size

    override fun onBindViewHolder(holder: GameHolder, position: Int) {
        holder.render(games[position])
        holder.view.setOnClickListener {
            onClickOpenDetails(games[position])
        }
    }
}