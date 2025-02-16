package com.example.gestion_juegos_kotlin.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gestion_juegos_kotlin.tabviews.DescriptionFragment
import com.example.gestion_juegos_kotlin.tabviews.DetailsFragment
import com.example.gestion_juegos_kotlin.tabviews.ReleasesFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {
    private val fragments = listOf(DescriptionFragment(), DetailsFragment(), ReleasesFragment())

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}