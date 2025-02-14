package com.example.gestion_juegos_kotlin

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gestion_juegos_kotlin.databinding.FragmentCollectionBinding

class CollectionFragment : Fragment() {
    lateinit var binding: FragmentCollectionBinding

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
        binding.collectioRecycler.layoutManager = GridLayoutManager(context, 2)
        TODO("Añadir Adaptador de colección")
        val adapter = CollectionAdapter() {onClickOpenDetails()}
        binding.collectioRecycler.adapter = adapter

    }

    private fun onClickOpenDetails() {
        TODO("Hacer clase Details")
        startActivity(
            Intent(context, Details::class.java)
        )
    }
}