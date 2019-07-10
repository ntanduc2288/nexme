package com.nexme.presentation.ui.onboarding.login

import android.content.Intent
import com.nexme.R
import com.nexme.presentation.ui.BaseFragment
import com.nexme.presentation.ui.explore.MapsActivity
import kotlinx.android.synthetic.main.login_view.*

class LoginFragment: BaseFragment() {


    companion object {
        fun newInstance(): LoginFragment{
            return LoginFragment()
        }
    }
    override fun getLayoutId() = R.layout.login_view

    override fun setupViews() {
        btnSkip.setOnClickListener { openHomePage() }
        btnBack.setOnClickListener { getCurrentActivity().onBackPressed() }
    }

    private fun openHomePage() {
        startActivity(Intent(context, MapsActivity::class.java))
        getCurrentActivity().finish()
    }
}