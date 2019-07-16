package com.nexme.presentation.ui.welcome

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.nexme.R
import com.nexme.presentation.manager.NexMeUserManager
import com.nexme.presentation.ui.BaseActivity
import com.nexme.presentation.ui.home.HomeActivity
import com.nexme.presentation.ui.onboarding.OnboardingActivity

class WelcomeActivity: BaseActivity() {
    override fun getLayoutId() = R.layout.welcome_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
    }

    private fun initViews(){

        val handler = Handler()
        handler.postDelayed({
            this@WelcomeActivity.runOnUiThread {
                openNexScreen()
            }
        }, 2 * 1000)
    }

    private fun openNexScreen(){
        if (NexMeUserManager.userObject == null) {
            startActivity(Intent(applicationContext, OnboardingActivity::class.java))
        }else {
            startActivity(Intent(applicationContext, HomeActivity::class.java))
        }
        finish()
    }
}