package com.nexme.presentation.ui.onboarding.signupcode

import android.os.Build
import android.telephony.PhoneNumberUtils
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nexme.presentation.model.SignupObject
import com.nexme.presentation.ui.BaseLiveDataFragment
import com.nexme.presentation.ui.onboarding.signupname.SignupNameFragment
import com.nexme.presentation.utils.AndroidUtil
import com.nexme.presentation.utils.pushFragment
import kotlinx.android.synthetic.main.signup_code.*
import kotlinx.android.synthetic.main.signup_mobile.btnBack
import kotlinx.android.synthetic.main.signup_mobile.btnNext
import java.util.*


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
        signupCodeViewModel.signupObject = signupObject
    }

    override fun getCurrentViewModel() = signupCodeViewModel

    override fun subscribeObservers() {
        signupCodeViewModel.signupCodeLiveData.observe(this, phoneCodeObserver)
    }

    override fun getLayoutId() = com.nexme.R.layout.signup_code

    override fun setupViews() {
        signupObject?.let {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                lblPhoneNumber.text = PhoneNumberUtils.formatNumber(it.phoneNumber, Locale.getDefault().country)
            } else {
                lblPhoneNumber.text = PhoneNumberUtils.formatNumber(it.phoneNumber)
            }
        }

        btnBack.setOnClickListener { getCurrentActivity().onBackPressed() }


        btnNext.setOnClickListener { onNextClicked() }

        edt1.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (edt1.text.toString().isNotEmpty()){
                    edt2.requestFocus()
                    shouldEnableNextButton()
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        edt2.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (edt2.text.toString().isNotEmpty()){
                    edt3.requestFocus()
                    shouldEnableNextButton()
                }
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        edt3.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                if (edt3.text.toString().isNotEmpty()){
                    edt4.requestFocus()
                }
                shouldEnableNextButton()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        edt4.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                shouldEnableNextButton()
            }
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })
    }

    private fun shouldEnableNextButton(){
        btnNext.isEnabled = AndroidUtil.isValidCode(getCode())
    }

    private fun getCode(): String {
        return edt1.text.toString().trim() + edt2.text.toString().trim()  + edt3.text.toString().trim() + edt4.text.toString().trim()
    }

    private val phoneCodeObserver = Observer<String> { verificationCode ->
        signupObject?.let {
            it.verificationCode = verificationCode.toInt()

            pushFragment(getCurrentActivity(), SignupNameFragment.newInstance(it), true)
        }

    }


    private fun onNextClicked() {
        val code = getCode()
        signupCodeViewModel.onNextClicked(code)

    }
}