package com.nexme.presentation.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ncapdevi.fragnav.FragNavController
import com.nexme.R
import com.nexme.presentation.ui.BaseActivity
import com.nexme.presentation.ui.onboarding.tour.TourFragment

class HomeActivity: BaseActivity(), FragNavController.RootFragmentListener {
    private val INDEX_EXPLORE = 0
    private val INDEX_FAVORITES = 1
    private val INDEX_HISTORY = 2
    private val INDEX_SERVICES = 3
    private val INDEX_MY_NEXME = 4

    private val fragNavController: FragNavController = FragNavController(supportFragmentManager, R.id.container)

    override fun getLayoutId() = R.layout.home_activity


    override val numberOfRootFragments: Int
        get() = 5

    override fun getRootFragment(index: Int): Fragment {
        when (index) {
            INDEX_EXPLORE -> return TourFragment.newInstance()
            INDEX_FAVORITES -> return TourFragment.newInstance()
            INDEX_HISTORY -> return TourFragment.newInstance()
            INDEX_SERVICES -> return TourFragment.newInstance()
            INDEX_MY_NEXME -> return TourFragment.newInstance()
        }
        throw IllegalStateException("Need to send an index that we know")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fragNavController.initialize(INDEX_EXPLORE, savedInstanceState)
    }
}