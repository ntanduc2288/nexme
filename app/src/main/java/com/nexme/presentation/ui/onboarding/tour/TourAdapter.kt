package com.nexme.presentation.ui.onboarding.tour

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TourAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    private val NUM_ITEMS = 4

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> Page1Fragment.newInstance()
            1 -> Page2Fragment.newInstance()
            2 -> Page3Fragment.newInstance()
            3 -> Page4Fragment.newInstance()
            else -> Page1Fragment.newInstance()
        }

    }

    override fun getCount() = NUM_ITEMS
}