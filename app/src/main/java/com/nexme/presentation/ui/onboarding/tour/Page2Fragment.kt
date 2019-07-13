package com.nexme.presentation.ui.onboarding.tour

import com.nexme.R
import com.nexme.presentation.ui.BaseFragment

class Page2Fragment: BaseFragment() {
    companion object{
        fun newInstance():Page2Fragment{
            return Page2Fragment()
        }
    }

    override fun getLayoutId() = R.layout.page2_fragment

    override fun setupViews() {
    }
}