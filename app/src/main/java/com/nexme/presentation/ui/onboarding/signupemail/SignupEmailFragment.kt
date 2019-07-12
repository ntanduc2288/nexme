package com.nexme.presentation.ui.onboarding.signupemail

import androidx.lifecycle.ViewModelProviders
import com.nexme.R
import com.nexme.presentation.model.SignupObject
import com.nexme.presentation.ui.BaseLiveDataFragment
import com.nexme.presentation.ui.onboarding.signuppassword.SignupPasswordFragment
import com.nexme.presentation.utils.pushFragment
import kotlinx.android.synthetic.main.signup_email.*

class SignupEmailFragment: BaseLiveDataFragment() {
    lateinit var signupEmailViewModel: SignupEmailViewModel
    var signupObject: SignupObject? = null

    companion object{
        fun newInstance(signupObject: SignupObject): SignupEmailFragment {
            val instance = SignupEmailFragment()
            instance.signupObject = signupObject
            return instance
        }
    }

    override fun registerViewModels() {
        signupEmailViewModel = ViewModelProviders.of(this).get(SignupEmailViewModel::class.java)

    }

    override fun getCurrentViewModel() = signupEmailViewModel

    override fun subscribeObservers() {
    }

    override fun getLayoutId() = R.layout.signup_email

    override fun setupViews() {


        btnBack.setOnClickListener { getCurrentActivity().onBackPressed() }


        btnNext.setOnClickListener { onNextClicked() }
    }


    private fun onNextClicked() {

        pushFragment(getCurrentActivity(), SignupPasswordFragment.newInstance(signupObject!!), true)


    }
}