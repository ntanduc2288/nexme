package com.nexme.presentation.ui.explore

import androidx.lifecycle.ViewModelProviders
import com.nexme.R
import com.nexme.presentation.ui.BaseLiveDataFragment
import com.nexme.presentation.ui.BaseViewModel

class ExploreFragment: BaseLiveDataFragment() {
    private lateinit var exploreViewModel: ExploreViewModel

    companion object {
        fun newInstance(): ExploreFragment {
            return ExploreFragment()
        }
    }

    override fun registerViewModels() {
        exploreViewModel = ViewModelProviders.of(this).get(ExploreViewModel::class.java)
    }

    override fun getCurrentViewModel() = exploreViewModel

    override fun subscribeObservers() {
    }

    override fun getLayoutId() = R.layout.explorer_layout

    override fun setupViews() {
    }
}