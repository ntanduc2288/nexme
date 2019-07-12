package com.nexme.presentation.ui.onboarding.signupname

import androidx.lifecycle.ViewModelProviders
import com.nexme.R
import com.nexme.presentation.model.SignupObject
import com.nexme.presentation.ui.BaseLiveDataFragment
import com.nexme.presentation.ui.onboarding.signupemail.SignupEmailFragment
import com.nexme.presentation.utils.pushFragment
import kotlinx.android.synthetic.main.signup_name.*

class SignupNameFragment: BaseLiveDataFragment() {

    lateinit var signupNameViewModel: SignupNameViewModel
    var signupObject: SignupObject? = null

    companion object{
        fun newInstance(signupObject: SignupObject): SignupNameFragment {
            val instance = SignupNameFragment()
            instance.signupObject = signupObject
            return instance
        }
    }

    override fun registerViewModels() {
        signupNameViewModel = ViewModelProviders.of(this).get(SignupNameViewModel::class.java)

    }

    override fun getCurrentViewModel() = signupNameViewModel

    override fun subscribeObservers() {
    }

    override fun getLayoutId() = R.layout.signup_name

    override fun setupViews() {


        btnBack.setOnClickListener { getCurrentActivity().onBackPressed() }


        btnNext.setOnClickListener { onNextClicked() }
    }


    private fun onNextClicked() {

        pushFragment(getCurrentActivity(), SignupEmailFragment.newInstance(signupObject!!), true)


    }
}