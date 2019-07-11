package com.nexme.presentation.ui.onboarding

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.jaychang.sa.AuthCallback
import com.jaychang.sa.BuildConfig
import com.jaychang.sa.SocialUser
import com.jaychang.sa.facebook.SimpleAuth
import com.nexme.domain.nexme.login.UserInteractor
import com.nexme.domain.nexme.login.UserInteractorImpl
import com.nexme.presentation.ui.BaseViewModel
import com.nexme.presentation.ui.onboarding.login.UserObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class OnboardingViewModel: BaseViewModel() {

    val loginLiveData: MutableLiveData<UserObject> by lazy { MutableLiveData<UserObject>() }
    var userInteractor: UserInteractor = UserInteractorImpl()

    fun onGoogleClicked(context: Context){
        showToast("Coming soon")

        val scopes = Arrays.asList(
            "https://www.googleapis.com/auth/youtube",
            "https://www.googleapis.com/auth/youtube.upload"
        )
        com.jaychang.sa.google.SimpleAuth.connectGoogle(scopes, object: AuthCallback{
            override fun onSuccess(socialUser: SocialUser?) {
                showToast("Success. " + socialUser.toString())
            }

            override fun onError(error: Throwable?) {
                showToast(error?.message ?: "Error")
            }

            override fun onCancel() {
                showToast("Canceled")
            }

        })
    }

    fun onFacebookClicked(context: Context){
        SimpleAuth.connectFacebook(emptyArray<String>().toList(),  object: AuthCallback{
            override fun onSuccess(socialUser: SocialUser) {
                loginSocial(context, "facebook", socialUser.accessToken)
            }

            override fun onError(error: Throwable?) {
                showToast(error?.message ?: "Error")
            }

            override fun onCancel() {
                showToast("Canceled")
            }
        })

    }

    fun onSignUpWithPhoneClicked(){
        showToast("Coming soon")
    }

    @SuppressLint("CheckResult")
    private fun loginSocial(context: Context, provider: String, accessToken: String) {
        showProgressDialog()
        userInteractor.loginSocial(context, provider, accessToken, !BuildConfig.DEBUG)
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