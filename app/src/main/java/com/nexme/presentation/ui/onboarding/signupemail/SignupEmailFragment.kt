package com.nexme.presentation.ui.onboarding.signupemail

import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nexme.R
import com.nexme.presentation.model.SignupObject
import com.nexme.presentation.ui.BaseLiveDataFragment
import com.nexme.presentation.ui.onboarding.signuppassword.SignupPasswordFragment
import com.nexme.presentation.utils.AndroidUtil
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
        signupEmailViewModel.emailLiveData.observe(this, emailValidObserver)
    }

    override fun getLayoutId() = R.layout.signup_email

    override fun setupViews() {


        btnBack.setOnClickListener { getCurrentActivity().onBackPressed() }


        btnNext.setOnClickListener { onNextClicked() }

        edtEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                shouldEnableNextButton()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })
        shouldEnableNextButton()
    }

    private fun shouldEnableNextButton() {
        btnNext.isEnabled = AndroidUtil.isValidEmail(edtEmail.text.toString().trim())
    }


    private fun onNextClicked() {

        signupObject?.let {
            signupEmailViewModel.onNextClicked(it, edtEmail.text.toString().trim())
        }




    }

    private val emailValidObserver = Observer<SignupObject> {

        pushFragment(getCurrentActivity(), SignupPasswordFragment.newInstance(it), true)
    }
}