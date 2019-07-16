package com.nexme.presentation.ui.onboarding.tour

import android.os.Bundle
import com.nexme.R
import com.nexme.presentation.ui.BaseActivity
import com.nexme.presentation.utils.pushFragment

class TourActivity: BaseActivity() {
    override fun getLayoutId() = R.layout.tour_activity


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pushFragment(this@TourActivity, TourFragment.newInstance(), false)
    }
}