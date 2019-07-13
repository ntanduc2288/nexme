package com.nexme.presentation.ui.onboarding.tour

import com.nexme.R
import com.nexme.presentation.ui.BaseFragment

class Page3Fragment: BaseFragment() {
    companion object{
        fun newInstance():Page3Fragment{
            return Page3Fragment()
        }
    }
    override fun getLayoutId() = R.layout.page3_fragment

    override fun setupViews() {
    }
}