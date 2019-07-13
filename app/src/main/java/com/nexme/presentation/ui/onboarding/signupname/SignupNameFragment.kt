package com.nexme.presentation.ui.onboarding.signupname

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProviders
import com.nexme.R
import com.nexme.presentation.model.SignupObject
import com.nexme.presentation.ui.BaseLiveDataFragment
import com.nexme.presentation.ui.onboarding.signupemail.SignupEmailFragment
import com.nexme.presentation.utils.AndroidUtil
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

        edtFirstName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                shouldEnableNextButton()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        edtLastName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                shouldEnableNextButton()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        shouldEnableNextButton()
    }

    private fun shouldEnableNextButton() {
        btnNext.isEnabled = AndroidUtil.isValidName(edtFirstName.text.toString().trim(), edtLastName.text.toString().trim())
    }


    private fun onNextClicked() {
        signupObject?.firstName = edtFirstName.text.toString().trim()
        signupObject?.lastName = edtLastName.text.toString().trim()

        pushFragment(getCurrentActivity(), SignupEmailFragment.newInstance(signupObject!!), true)


    }
}