package com.nexme.presentation.ui.onboarding.signupcode

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.nexme.R
import com.nexme.domain.nexme.login.UserInteractorImpl
import com.nexme.presentation.model.SignupObject
import com.nexme.presentation.ui.BaseViewModel
import com.nexme.presentation.ui.NexMeApp
import com.nexme.presentation.ui.onboarding.signupmobile.authyApiKey
import com.nexme.presentation.utils.DateUtils
import com.nexme.presentation.utils.ErrorParsing
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


class SignupCodeViewModel : BaseViewModel() {

    val signupCodeLiveData: MutableLiveData<String> by lazy { MutableLiveData<String>() }
    val secondToExpireLiveData: MutableLiveData<Int> by lazy { MutableLiveData<Int>() }
    var signupObject: SignupObject? = null
    private val userInteractor = UserInteractorImpl()
    private var countDownDisposable: Disposable? = null

    private val disposables = CompositeDisposable()

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
                { phoneCodeResponseEntity -> verifySuccessfully(verificationCode) },
                { error -> errorOccurs(error) })

    }

    private fun verifySuccessfully(verificationCode: String) {
        hideProgressDialog()
        signupCodeLiveData.value = verificationCode
    }

    private fun errorOccurs(error: Throwable) {
        hideProgressDialog()
        error.message?.let { showToast(ErrorParsing.parse(error)) }
    }

    fun resendCode() {
        if (signupObject != null && signupObject?.phoneNumber != null && signupObject?.countryCode != null){
            userInteractor.requestPhoneVerification(authyApiKey, signupObject!!.countryCode!!, signupObject!!.phoneNumber!!)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ phoneResponseEntity ->

                    signupObject?.secondToExpire = phoneResponseEntity.seconds_to_expire
                    secondToExpireLiveData.value = phoneResponseEntity.seconds_to_expire
                }, { error -> errorOccurs(error) })
        }
    }



}