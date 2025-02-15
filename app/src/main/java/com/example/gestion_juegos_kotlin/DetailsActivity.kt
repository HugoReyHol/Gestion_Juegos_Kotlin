package com.example.gestion_juegos_kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.gestion_juegos_kotlin.databinding.ActivityDetailsBinding
import com.example.gestion_juegos_kotlin.models.Game
import com.example.gestion_juegos_kotlin.models.UserGame
import com.example.gestion_juegos_kotlin.providers.GamesProvider
import com.example.gestion_juegos_kotlin.providers.UserGamesProvider
import com.example.gestion_juegos_kotlin.services.UserGameService
import kotlinx.coroutines.launch

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private var game: Game = GamesProvider.selectedGame
    private var userGame: UserGame? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        userGame = UserGamesProvider.userGames.firstOrNull { ug -> ug.idGame == game.idGame }

        initializeComponents()
    }

    private fun initializeComponents() {
        binding.gameCover.setImageBitmap(game.image)

        if (userGame == null) {
            // TODO Mostrar botón añadir

        } else {
            // TODO Mostrar formulario

        }

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = game.title

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressedDispatcher.onBackPressed()
            R.id.delete_btn -> {
                lifecycleScope.launch {
                    if (UserGameService.deleteUserGame(userGame!!)) {
                        // TODO borrar de lista
                        updateUI()
                    }
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    @SuppressLint("RestrictedApi")
    private fun updateUI() {
        // TODO acabar esto
        if (userGame == null) {
            supportActionBar?.dispatchMenuVisibilityChanged(false)

        } else {
            supportActionBar?.dispatchMenuVisibilityChanged(false)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (userGame != null) menuInflater.inflate(R.menu.menu_details, menu)

        return super.onCreateOptionsMenu(menu)
    }
}