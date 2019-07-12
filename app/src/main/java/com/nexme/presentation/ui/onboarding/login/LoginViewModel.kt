package com.nexme.presentation.ui.onboarding.login

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.nexme.BuildConfig
import com.nexme.R
import com.nexme.domain.nexme.login.UserInteractor
import com.nexme.domain.nexme.login.UserInteractorImpl
import com.nexme.presentation.ui.App
import com.nexme.presentation.ui.BaseViewModel
import com.nexme.presentation.utils.ErrorParsing
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class LoginViewModel: BaseViewModel() {
    private var userInteractor: UserInteractor = UserInteractorImpl()
    val loginLiveData: MutableLiveData<UserObject> by lazy { MutableLiveData<UserObject>() }

    @SuppressLint("CheckResult")
    fun onLoginClicked(context: Context, password: String, uid: String){
        showProgressDialog()
        userInteractor.login(context, password, uid, !BuildConfig.DEBUG)
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


        error.message?.let { showToast(parseError(error)) }
    }

    fun parseError(error: Throwable): String{
        var errorMessage = App.applicationContext().getString(R.string.something_went_wrong)

        if (error is HttpException) {
            if (error.code() == 422){
                if (error.response()?.errorBody()?.string() == "false") {
                    errorMessage = App.applicationContext().getString(R.string.wrong_username_password)
                    return errorMessage
                }
            }

            if (error.code() == 400) {
                errorMessage = App.applicationContext().getString(R.string.wrong_username_password)
                return errorMessage
            }
            errorMessage = ErrorParsing.parse(error)
        }

        return errorMessage
    }
}