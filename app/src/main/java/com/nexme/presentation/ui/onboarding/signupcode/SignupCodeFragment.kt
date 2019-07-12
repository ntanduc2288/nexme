package com.nexme.presentation.ui.onboarding.signupmobile

import androidx.lifecycle.ViewModelProviders
import com.nexme.R
import com.nexme.presentation.model.SignupObject
import com.nexme.presentation.ui.BaseLiveDataFragment
import com.nexme.presentation.ui.onboarding.signupcode.SignupCodeViewModel
import kotlinx.android.synthetic.main.signup_code.*
import kotlinx.android.synthetic.main.signup_mobile.btnBack
import kotlinx.android.synthetic.main.signup_mobile.btnNext


class SignupCodeFragment: BaseLiveDataFragment() {
    lateinit var signupCodeViewModel: SignupCodeViewModel
    var signupObject: SignupObject? = null

    companion object{
        fun newInstance(signupObject: SignupObject): SignupCodeFragment {
            val instance = SignupCodeFragment()
            instance.signupObject = signupObject
            return instance
        }
    }

    override fun registerViewModels() {
        signupCodeViewModel = ViewModelProviders.of(this).get(SignupCodeViewModel::class.java)

    }

    override fun getCurrentViewModel() = signupCodeViewModel

    override fun subscribeObservers() {
    }

    override fun getLayoutId() = R.layout.signup_code

    override fun setupViews() {

        lblPhoneNumber.text = signupObject?.phoneNumber

        btnBack.setOnClickListener { getCurrentActivity().onBackPressed() }


        btnNext.setOnClickListener { onNextClicked() }
    }


    private fun onNextClicked() {




    }
}