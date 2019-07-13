package com.nexme.presentation.ui.onboarding.signupemail

import androidx.lifecycle.MutableLiveData
import com.nexme.R
import com.nexme.domain.nexme.login.UserInteractor
import com.nexme.domain.nexme.login.UserInteractorImpl
import com.nexme.presentation.model.SignupObject
import com.nexme.presentation.ui.BaseViewModel
import com.nexme.presentation.ui.NexMeApp
import com.nexme.presentation.utils.CODE_402
import com.nexme.presentation.utils.ErrorParsing
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

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
                signupObject.email = email
                emailLiveData.value = signupObject

            }, {error -> errorOccurs(error) })
    }

    private fun errorOccurs(error: Throwable) {
        hideProgressDialog()

        var errorMessage = ErrorParsing.parse(error)
        if (error is HttpException) {
            if (error.code() == CODE_402){
                if (error.response()?.errorBody()?.string() == "false") {
                    errorMessage = NexMeApp.applicationContext().getString(R.string.email_already_in_use)
                }
            }
        }


        showToast(errorMessage)
    }
}