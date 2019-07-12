package com.nexme.presentation.ui.onboarding.signupmobile

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.nexme.data.model.response.twilio.PhoneResponseEntity
import com.nexme.domain.nexme.login.UserInteractorImpl
import com.nexme.presentation.model.SignupObject
import com.nexme.presentation.ui.App
import com.nexme.presentation.ui.BaseViewModel
import com.nexme.presentation.ui.onboarding.login.UserObject
import com.nexme.presentation.utils.ErrorParsing
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

const val authyApiKey = "ZEYJi9weVWybHJK6lZIOkxy6GQm05uDq"
class SignupMobileViewModel: BaseViewModel() {

    val userInteractor = UserInteractorImpl()
    val phoneResponseLiveData: MutableLiveData<SignupObject> by lazy { MutableLiveData<SignupObject>() }
    private var countryCode: String? = null
    private var phoneNumber: String? = null

    @SuppressLint("CheckResult")
    fun onNextClicked(countryCode: String, phoneNumber: String){
        showProgressDialog()
        this.countryCode = countryCode
        this.phoneNumber = phoneNumber
        userInteractor.requestPhoneVerification(authyApiKey, countryCode, phoneNumber)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ phoneResponseEntity -> verifySuccessfully(phoneResponseEntity) }, { error -> errorOccurs(error) })

    }

    private fun verifySuccessfully(phoneResponseEntity: PhoneResponseEntity) {
        hideProgressDialog()
        val signupObject = SignupObject()
        signupObject.countryCode = countryCode
        signupObject.phoneNumber = phoneNumber
        signupObject.secondToExpire = phoneResponseEntity.seconds_to_expire
        phoneResponseLiveData.value = signupObject
    }

    private fun errorOccurs(error: Throwable) {
        hideProgressDialog()
        error.message?.let { showToast(ErrorParsing.parse(error)) }
    }
}