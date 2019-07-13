package com.nexme.presentation.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.nexme.R
import com.nexme.presentation.ui.BaseActivity
import com.nexme.presentation.ui.onboarding.OnboardingActivity

class WelcomeActivity: BaseActivity() {
    override fun getLayoutId() = R.layout.welcome_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val handler = Handler()
        handler.postDelayed({
            this@WelcomeActivity.runOnUiThread {
                openOnBoarding()
            }
        }, 2 * 1000)

    }

    private fun openOnBoarding(){
        startActivity(Intent(applicationContext, OnboardingActivity::class.java))
        finish()
    }
}