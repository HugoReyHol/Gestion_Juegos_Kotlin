package com.example.gestion_juegos_kotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.gestion_juegos_kotlin.databinding.ActivityDetailsBinding
import com.example.gestion_juegos_kotlin.models.Game
import com.example.gestion_juegos_kotlin.models.UserGame
import com.example.gestion_juegos_kotlin.models.UserGame.Companion.GameStates
import com.example.gestion_juegos_kotlin.models.UserGameUpdate
import com.example.gestion_juegos_kotlin.providers.GamesProvider
import com.example.gestion_juegos_kotlin.providers.HomeProvider
import com.example.gestion_juegos_kotlin.providers.UserGamesProvider
import com.example.gestion_juegos_kotlin.providers.UserProvider
import com.example.gestion_juegos_kotlin.services.UserGameService
import kotlinx.coroutines.launch

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private var game: Game = GamesProvider.selectedGame
    private var userGame: UserGame? = null
    private var initialized: Boolean = false
    private val stateOptions = GameStates.entries.map { it.string }

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
        updateUI()
        initialized = true
    }

    private fun initializeComponents() {
        binding.gameCover.setImageBitmap(game.image)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = game.title

        binding.addBtn.setOnClickListener {
            userGame = UserGame(game.idGame, UserProvider.user!!.idUser)
            lifecycleScope.launch {
                if (UserGameService.insertUserGame(userGame!!)) {
                    UserGamesProvider.addUserGame(userGame!!)
                    updateUI()
                } else {
                    userGame = null
                    Toast.makeText(applicationContext, "No se pudo guardar el juego", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // TODO configurar form
        binding.stateValue.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, stateOptions)

        // TODO arreglar que llame a update al abrir la ventana
        binding.stateValue.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (userGame != null && initialized) {
                     val state = GameStates.entries[position]

                    lifecycleScope.launch {
                        if (UserGameService.updateUserGame(userGame!!, UserGameUpdate(gameState = GameStates.entries[position].name))) {
                            userGame!!.gameState = state
                            HomeProvider.deleteUserGameFromFilter(userGame!!)

                        } else {
                            Toast.makeText(applicationContext, "Error al actualizar el juego", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressedDispatcher.onBackPressed()
            R.id.delete_btn -> {
                // TODO Añadir confirmación

                lifecycleScope.launch {
                    if (UserGameService.deleteUserGame(userGame!!)) {
                        UserGamesProvider.deleteUserGame(userGame!!)
                        userGame = null
                        updateUI()

                    } else {
                        Toast.makeText(applicationContext, "No se pudo borrar el juego", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_details, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        menu?.findItem(R.id.delete_btn)?.isVisible = userGame != null
        return super.onPrepareOptionsMenu(menu)
    }

    @SuppressLint("RestrictedApi")
    private fun updateUI() {
        // TODO acabar esto
        if (userGame == null) {
            invalidateOptionsMenu()
            binding.gameForm.visibility = View.INVISIBLE
            binding.addBtn.visibility = View.VISIBLE

        } else {
            invalidateOptionsMenu()
            binding.gameForm.visibility = View.VISIBLE
            binding.addBtn.visibility = View.INVISIBLE

            // TODO cargar los valores del userGame en el form
            binding.stateValue.setSelection(stateOptions.indexOf(userGame!!.gameState.string))
        }
    }
}