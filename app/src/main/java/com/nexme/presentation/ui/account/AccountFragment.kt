package com.nexme.presentation.ui.account

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.nexme.R
import com.nexme.presentation.manager.NexMeUserManager
import com.nexme.presentation.ui.BaseLiveDataFragment
import com.nexme.presentation.ui.onboarding.OnboardingActivity
import kotlinx.android.synthetic.main.acount_layout.*

class AccountFragment: BaseLiveDataFragment() {
    private lateinit var accountViewModel: AccountViewModel

    companion object {
        fun newInstance(): AccountFragment {
            return AccountFragment()
        }
    }

    override fun registerViewModels() {
        accountViewModel = ViewModelProviders.of(this).get(AccountViewModel::class.java)
    }

    override fun getCurrentViewModel() = accountViewModel

    override fun subscribeObservers() {
    }

    override fun getLayoutId() = R.layout.acount_layout

    override fun setupViews() {

        btnSignOut.setOnClickListener { signOut()}

        if (NexMeUserManager.userObject != null) {
            btnSignOut.visibility = View.VISIBLE
            lblUserName.text = NexMeUserManager.userObject?.fullname + "\n" + NexMeUserManager.userObject?.email
        }else {
            btnSignOut.visibility = View.GONE
        }

    }

    private fun signOut(){
        NexMeUserManager.userObject = null
        startActivity(Intent(context, OnboardingActivity::class.java))
        getCurrentActivity().finish()
    }
}