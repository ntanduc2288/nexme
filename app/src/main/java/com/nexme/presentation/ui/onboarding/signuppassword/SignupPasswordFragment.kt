package com.nexme.presentation.ui.onboarding.signuppassword

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProviders
import com.nexme.R
import com.nexme.presentation.model.SignupObject
import com.nexme.presentation.ui.BaseLiveDataFragment
import com.nexme.presentation.ui.explore.MapsActivity
import com.nexme.presentation.utils.AndroidUtil
import kotlinx.android.synthetic.main.signup_email.*
import kotlinx.android.synthetic.main.signup_password.*
import kotlinx.android.synthetic.main.signup_password.btnBack
import kotlinx.android.synthetic.main.signup_password.btnNext

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

        edtPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                shouldEnableNextButton()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        shouldEnableNextButton()
    }

    private fun shouldEnableNextButton() {
        btnNext.isEnabled = AndroidUtil.isValidPassword(edtPassword.text.toString().trim())
    }


    private fun onNextClicked() {

        startActivity(Intent(getCurrentActivity(), MapsActivity::class.java))
        getCurrentActivity().finish()


    }
}