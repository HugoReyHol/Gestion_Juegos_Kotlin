package com.example.gestion_juegos_kotlin

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.example.gestion_juegos_kotlin.databinding.ActivityMainBinding
import com.example.gestion_juegos_kotlin.models.UserGame.Companion.GameStates
import com.example.gestion_juegos_kotlin.providers.GamesProvider
import com.example.gestion_juegos_kotlin.providers.HomeProvider
import com.example.gestion_juegos_kotlin.providers.UserGamesProvider
import com.example.gestion_juegos_kotlin.util.GamesFragment
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initializeComponents()
    }

    private fun initializeComponents() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = ""

        val options = GameStates.entries.map { it.string } as MutableList
        options.add("TODOS")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, options)
        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // TODO probar estados
                lifecycleScope.launch {
                    HomeProvider.initialize()
                    HomeProvider.filterHomeGames(state = GameStates.entries.getOrNull(position))
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.collection_nav -> {
                    supportFragmentManager.commit {
                        replace(R.id.fragmentContainerView, CollectionFragment())
                    }
                    binding.spinner.visibility = View.VISIBLE
                }
                R.id.game_nav -> {
                    supportFragmentManager.commit {
                        replace(R.id.fragmentContainerView, GamesFragment())
                    }
                    binding.spinner.visibility = View.INVISIBLE
                }
            }

            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_home, menu)

        val searchItem: MenuItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                // TODO probar esto
                if (text != null) {
                    GamesProvider.filterGamesByTitle(text)
                } else {
                    GamesProvider.filterGamesByTitle()
                }

                return false
            }
        })

        return true
    }
}