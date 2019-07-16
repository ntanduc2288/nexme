package com.nexme.presentation.ui.explore

import androidx.lifecycle.ViewModelProviders
import com.nexme.R
import com.nexme.presentation.ui.BaseLiveDataFragment

class FavoritesFragment: BaseLiveDataFragment() {
    private lateinit var favoriteViewModel: FavoritesViewModel

    companion object {
        fun newInstance(): FavoritesFragment {
            return FavoritesFragment()
        }
    }

    override fun registerViewModels() {
        favoriteViewModel = ViewModelProviders.of(this).get(FavoritesViewModel::class.java)
    }

    override fun getCurrentViewModel() = favoriteViewModel

    override fun subscribeObservers() {
    }

    override fun getLayoutId() = R.layout.favorites_layout

    override fun setupViews() {
    }
}