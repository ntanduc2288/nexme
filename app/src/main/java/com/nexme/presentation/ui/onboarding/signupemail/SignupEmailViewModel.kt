package com.nexme.presentation.ui.onboarding.signupemail

import androidx.lifecycle.MutableLiveData
import com.nexme.R
import com.nexme.domain.nexme.login.UserInteractor
import com.nexme.domain.nexme.login.UserInteractorImpl
import com.nexme.presentation.model.SignupObject
import com.nexme.presentation.ui.BaseViewModel
import com.nexme.presentation.ui.NexMeApp
import com.nexme.presentation.utils.CODE_422
import com.nexme.presentation.utils.ErrorParsing
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SignupEmailViewModel: BaseViewModel() {
    private var userInteractor: UserInteractor = UserInteractorImpl()

    val emailLiveData: MutableLiveData<SignupObject> by lazy { MutableLiveData<SignupObject>() }

    fun onNextClicked(signupObject: SignupObject, email: String) {
        showProgressDialog()
        userInteractor.checkUID(email)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({isValidUid ->
                hideProgressDialog()
                showToast(NexMeApp.applicationContext().getString(R.string.email_already_in_use))

            }, {error -> errorOccurs(error, signupObject, email ) })
    }

    private fun errorOccurs(error: Throwable, signupObject: SignupObject, email: String) {
        hideProgressDialog()

        if (error is com.jakewharton.retrofit2.adapter.rxjava2.HttpException) {
            if (error.code() == CODE_422) {

                signupObject.email = email
                emailLiveData.value = signupObject

                return
            }
        }


        showToast(ErrorParsing.parse(error))
    }
}