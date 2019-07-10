package com.nexme.presentation.ui.onboarding.login

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.nexme.domain.nexme.login.UserInteractor
import com.nexme.domain.nexme.login.UserInteractorImpl
import com.nexme.presentation.ui.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel: BaseViewModel() {
    private var userInteractor: UserInteractor = UserInteractorImpl()
    val loginLiveData: MutableLiveData<UserObject> by lazy { MutableLiveData<UserObject>() }

    @SuppressLint("CheckResult")
    fun onLoginClicked(context: Context, password: String, uid: String){
        showProgressDialog()
        userInteractor.login(context, password, uid)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({userObject -> loginSuccessfully(userObject)  }, {error -> errorOccurs(error) })

    }

    private fun loginSuccessfully(userObject: UserObject) {
        hideProgressDialog()
        showToast("Successfully login")
        loginLiveData.value = userObject
    }

    private fun errorOccurs(error: Throwable) {
        hideProgressDialog()
        error.message?.let { showToast(it) }
    }
}