package com.example.gestion_juegos_kotlin.tabviews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gestion_juegos_kotlin.R
import com.example.gestion_juegos_kotlin.databinding.FragmentDescriptionBinding
import com.example.gestion_juegos_kotlin.databinding.FragmentReleasesBinding
import com.example.gestion_juegos_kotlin.providers.GamesProvider

class ReleasesFragment : Fragment() {
    private lateinit var binding: FragmentReleasesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReleasesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.releasesText.text = GamesProvider.selectedGame.releases
    }
}