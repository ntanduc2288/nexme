package com.nexme.presentation.ui.onboarding.signupcode

import android.annotation.SuppressLint
import com.nexme.data.model.response.twilio.PhoneResponseEntity
import com.nexme.domain.nexme.login.UserInteractorImpl
import com.nexme.presentation.model.SignupObject
import com.nexme.presentation.ui.App
import com.nexme.presentation.ui.BaseViewModel
import com.nexme.presentation.ui.onboarding.signupmobile.authyApiKey
import com.nexme.presentation.utils.ErrorParsing
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignupCodeViewModel : BaseViewModel() {

    var signupObject: SignupObject? = null
    private val userInteractor = UserInteractorImpl()

    @SuppressLint("CheckResult")
    fun onNextClicked(verificationCode: String) {
        showProgressDialog()
        userInteractor.checkPhoneVerification(
            authyApiKey,
            signupObject?.countryCode ?: "",
            signupObject?.phoneNumber ?: "",
            verificationCode
        )
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { phoneResponseEntity -> verifySuccessfully(phoneResponseEntity) },
                { error -> errorOccurs(error) })

    }

    private fun verifySuccessfully(phoneResponseEntity: PhoneResponseEntity) {
        hideProgressDialog()
        showToast("Success")
    }

    private fun errorOccurs(error: Throwable) {
        hideProgressDialog()
        error.message?.let { showToast(ErrorParsing.parse(error)) }
    }
}