package com.nexme.presentation.ui.onboarding

import android.os.Bundle
import com.nexme.R
import com.nexme.presentation.ui.BaseActivity
import com.nexme.presentation.utils.pushFragment

class OnboardingActivity: BaseActivity() {

    override fun getLayoutId() = R.layout.onboarding_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pushFragment(this, OnBoardingFragment.newInstance(), false)
    }


}