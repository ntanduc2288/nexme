package com.nexme.presentation.ui.onboarding.signupmobile

import android.graphics.Typeface
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.ViewModelProviders
import com.hbb20.CountryCodePicker
import com.nexme.R
import com.nexme.presentation.model.SignupObject
import com.nexme.presentation.ui.BaseLiveDataFragment
import com.nexme.presentation.utils.pushFragment
import kotlinx.android.synthetic.main.signup_mobile.*



class SignupMobileFragment: BaseLiveDataFragment() {
    lateinit var signupMobileViewModel: SignupMobileViewModel

    companion object{
        fun newInstance(): SignupMobileFragment {
            return SignupMobileFragment()
        }
    }

    override fun registerViewModels() {
        signupMobileViewModel = ViewModelProviders.of(this).get(SignupMobileViewModel::class.java)

    }

    override fun getCurrentViewModel() = signupMobileViewModel

    override fun subscribeObservers() {
    }

    override fun getLayoutId() = R.layout.signup_mobile

    override fun setupViews() {
        btnBack.setOnClickListener { getCurrentActivity().onBackPressed() }

        setTTFfont(ccp, getString(R.string.montserrat_regular))

        btnNext.setOnClickListener { onNextClicked() }

//        formatPhoneNumber()
    }

    private fun formatPhoneNumber(){
        edtPhoneNumber.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                val text = edtPhoneNumber.getText().toString()
                val textLength = edtPhoneNumber.getText()!!.length
                if (text.endsWith("-") || text.endsWith(" ") || text.endsWith(" "))
                    return
                if (textLength == 1) {
                    if (!text.contains("(")) {
                        edtPhoneNumber.setText(StringBuilder(text).insert(text.length - 1, "(").toString())
                        edtPhoneNumber.setSelection(edtPhoneNumber.text!!.length)
                    }
                } else if (textLength == 5) {
                    if (!text.contains(")")) {
                        edtPhoneNumber.setText(StringBuilder(text).insert(text.length - 1, ")").toString())
                        edtPhoneNumber.setSelection(edtPhoneNumber.getText()!!.length)
                    }
                } else if (textLength == 6) {
                    edtPhoneNumber.setText(StringBuilder(text).insert(text.length - 1, " ").toString())
                    edtPhoneNumber.setSelection(edtPhoneNumber.getText()!!.length)
                } else if (textLength == 10) {
                    if (!text.contains("-")) {
                        edtPhoneNumber.setText(StringBuilder(text).insert(text.length - 1, "-").toString())
                        edtPhoneNumber.setSelection(edtPhoneNumber.getText()!!.length)
                    }
                } else if (textLength == 15) {
                    if (text.contains("-")) {
                        edtPhoneNumber.setText(StringBuilder(text).insert(text.length - 1, "-").toString())
                        edtPhoneNumber.setSelection(edtPhoneNumber.getText()!!.length)
                    }
                } else if (textLength == 18) {
                    if (text.contains("-")) {
                        edtPhoneNumber.setText(StringBuilder(text).insert(text.length - 1, "-").toString())
                        edtPhoneNumber.setSelection(edtPhoneNumber.getText()!!.length)
                    }
                }

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        })
    }


    private fun setTTFfont(ccp: CountryCodePicker, fontFileName: String) {
        val typeFace = Typeface.createFromAsset(context!!.assets, fontFileName)
        ccp.setTypeFace(typeFace)

    }

    private fun onNextClicked() {

        val signupObject = SignupObject()
        signupObject.phoneNumber = edtPhoneNumber.text.toString()
        signupObject.countryCode = ccp.selectedCountryCodeWithPlus
//        pushFragment(getCurrentActivity(), SignupCodeFragment.newInstance(signupObject), true)
        signupMobileViewModel.onNextClicked(ccp.selectedCountryCode, edtPhoneNumber.text.toString() )

    }
}