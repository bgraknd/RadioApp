package com.example.spotifycloneapp.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.spotifycloneapp.R
import com.example.spotifycloneapp.favorites.FavoritesFragment
import com.example.spotifycloneapp.radios.RadiosFragment

class MainPagerAdapter(
        context: Context,
        fragmentManager: FragmentManager,
        behavior: Int) :

        FragmentStatePagerAdapter(fragmentManager, behavior) {

    private val tabs = context.applicationContext.resources.getStringArray(R.array.tabs)

    override fun getItem(position: Int): Fragment {
        return when (position) {
            TAB_ITEM_RADIOS -> RadiosFragment.newInstance()
            TAB_ITEM_FAVORITES -> FavoritesFragment.newInstance()
            else -> throw IllegalStateException("Can not find fragment, position: $position")
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs[position].toUpperCase()
    }

    companion object {
        private const val TAB_ITEM_RADIOS = 0
        private const val TAB_ITEM_FAVORITES = 1
    }

}
