package com.nexme.presentation.ui.onboarding.signuppassword

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import com.nexme.R
import com.nexme.presentation.model.SignupObject
import com.nexme.presentation.ui.BaseLiveDataFragment
import com.nexme.presentation.ui.explore.MapsActivity
import kotlinx.android.synthetic.main.signup_password.*

class SignupPasswordFragment: BaseLiveDataFragment() {
    lateinit var signupPasswordViewModel: SignupPasswordViewModel
    var signupObject: SignupObject? = null

    companion object{
        fun newInstance(signupObject: SignupObject): SignupPasswordFragment {
            val instance = SignupPasswordFragment()
            instance.signupObject = signupObject
            return instance
        }
    }

    override fun registerViewModels() {
        signupPasswordViewModel = ViewModelProviders.of(this).get(SignupPasswordViewModel::class.java)

    }

    override fun getCurrentViewModel() = signupPasswordViewModel

    override fun subscribeObservers() {
    }

    override fun getLayoutId() = R.layout.signup_password

    override fun setupViews() {


        btnBack.setOnClickListener { getCurrentActivity().onBackPressed() }


        btnNext.setOnClickListener { onNextClicked() }
    }


    private fun onNextClicked() {

        startActivity(Intent(getCurrentActivity(), MapsActivity::class.java))
        getCurrentActivity().finish()


    }
}