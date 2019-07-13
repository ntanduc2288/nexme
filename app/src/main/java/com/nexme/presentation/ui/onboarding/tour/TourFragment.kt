package com.nexme.presentation.ui.onboarding.tour

import androidx.lifecycle.ViewModelProviders
import com.nexme.R
import com.nexme.presentation.ui.BaseLiveDataFragment
import kotlinx.android.synthetic.main.tour_fragment.*

class TourFragment: BaseLiveDataFragment() {
    private lateinit var tourViewModel: TourViewModel

    companion object{
        fun newInstance(): TourFragment{
            return TourFragment()
        }
    }

    override fun registerViewModels() {
        tourViewModel = ViewModelProviders.of(this).get(TourViewModel::class.java)
    }

    override fun getCurrentViewModel() = tourViewModel

    override fun subscribeObservers() {
    }

    override fun getLayoutId() = R.layout.tour_fragment

    override fun setupViews() {

        val tourAdapter = TourAdapter(getCurrentActivity().supportFragmentManager)
        vp.adapter = tourAdapter
        dotsIndicator.setViewPager(vp)
    }
}