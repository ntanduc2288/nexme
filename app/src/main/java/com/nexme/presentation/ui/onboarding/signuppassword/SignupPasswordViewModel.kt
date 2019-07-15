package com.nexme.presentation.ui.onboarding.signuppassword

import androidx.lifecycle.MutableLiveData
import com.nexme.BuildConfig
import com.nexme.domain.nexme.login.UserInteractor
import com.nexme.domain.nexme.login.UserInteractorImpl
import com.nexme.presentation.model.SignupObject
import com.nexme.presentation.ui.BaseViewModel
import com.nexme.presentation.ui.onboarding.login.UserObject
import com.nexme.presentation.utils.ErrorParsing
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignupPasswordViewModel: BaseViewModel() {

    val signupPhoneLiveData: MutableLiveData<UserObject> by lazy { MutableLiveData<UserObject>() }

    private val userInteractor: UserInteractor = UserInteractorImpl()

    fun onNextClicked(signupObject: SignupObject, password: String){
        showProgressDialog()
        userInteractor.signupPhone(signupObject.email ?:"", password, signupObject.phoneNumber ?:"", signupObject.firstName ?:"", signupObject.lastName ?:"", !BuildConfig.DEBUG )
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { userObject -> signUpPhoneSuccessful(userObject) },
                { error -> errorOccurs(error) })


    }

    private fun errorOccurs(error: Throwable) {
        hideProgressDialog()
        error.message?.let { showToast(ErrorParsing.parse(error)) }
    }

    private fun signUpPhoneSuccessful(userObject: UserObject) {
        hideProgressDialog()
        signupPhoneLiveData.value = userObject
    }
}