package com.nexme.presentation.ui.onboarding.tour

import com.nexme.R
import com.nexme.presentation.ui.BaseFragment

class Page1Fragment: BaseFragment() {

    companion object{
        fun newInstance():Page1Fragment{
            return Page1Fragment()
        }
    }

    override fun getLayoutId() = R.layout.page1_fragment

    override fun setupViews() {
    }
}