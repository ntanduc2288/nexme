package com.nexme.presentation.ui.onboarding.signupmobile

import android.annotation.SuppressLint
import com.nexme.data.model.response.twilio.PhoneResponseEntity
import com.nexme.domain.nexme.login.UserInteractorImpl
import com.nexme.presentation.ui.App
import com.nexme.presentation.ui.BaseViewModel
import com.nexme.presentation.ui.onboarding.login.UserObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignupMobileViewModel: BaseViewModel() {
    val authyApiKey = "ZEYJi9weVWybHJK6lZIOkxy6GQm05uDq"
    val userInteractor = UserInteractorImpl()

    @SuppressLint("CheckResult")
    fun onNextClicked(countryCode: String, phoneNumber: String){
        showProgressDialog()
        userInteractor.requestPhoneVerification(App.applicationContext(), authyApiKey, countryCode, phoneNumber)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ phoneResponseEntity -> verifySuccessfully(phoneResponseEntity) }, { error -> errorOccurs(error) })

    }

    private fun verifySuccessfully(phoneResponseEntity: PhoneResponseEntity) {
        hideProgressDialog()
        showToast("Successfully login")
    }

    private fun errorOccurs(error: Throwable) {
        hideProgressDialog()
        error.message?.let { showToast(it) }
    }
}