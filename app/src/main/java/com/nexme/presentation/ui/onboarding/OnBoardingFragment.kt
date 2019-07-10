package com.nexme.presentation.ui.onboarding

import com.nexme.R
import com.nexme.presentation.ui.BaseFragment

class OnBoardingFragment: BaseFragment() {
    companion object {
        fun newInstance(): OnBoardingFragment {
            return OnBoardingFragment()
        }
    }
    override fun getLayoutId() = R.layout.onboarding_view
}