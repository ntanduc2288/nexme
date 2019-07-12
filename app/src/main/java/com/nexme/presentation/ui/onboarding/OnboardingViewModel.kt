package com.nexme.presentation.ui.onboarding

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.jaychang.sa.AuthCallback
import com.jaychang.sa.BuildConfig
import com.jaychang.sa.SocialUser
import com.jaychang.sa.facebook.SimpleAuth
import com.nexme.domain.nexme.login.UserInteractor
import com.nexme.domain.nexme.login.UserInteractorImpl
import com.nexme.presentation.ui.App
import com.nexme.presentation.ui.BaseViewModel
import com.nexme.presentation.ui.onboarding.login.UserObject
import com.nexme.presentation.utils.ErrorParsing
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class OnboardingViewModel : BaseViewModel(), GoogleApiClient.OnConnectionFailedListener {
//    private val requestIDToken = "236473905821-fets70bkgbu22sj2p3gkbndd121s6hup.apps.googleusercontent.com"
    private val requestIDToken = "659355091265-acr98mn6p4kqkr0nhk0pafebjb0mlcoe.apps.googleusercontent.com"

    val loginLiveData: MutableLiveData<UserObject> by lazy { MutableLiveData<UserObject>() }
    val googleActivityLiveData: MutableLiveData<Intent> by lazy { MutableLiveData<Intent>() }
    var userInteractor: UserInteractor = UserInteractorImpl()
    var mGoogleApiClient: GoogleApiClient? = null
    val gso: GoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(requestIDToken)
        .requestProfile()
        .requestEmail()
        .build()

    fun onGoogleClicked(context: Context) {
//        val googleAccount = GoogleSignIn.getLastSignedInAccount(App.applicationContext())
//        if (googleAccount == null || googleAccount.isExpired || googleAccount.idToken.isNullOrEmpty()) {
//
//            if (mGoogleApiClient == null) {
//                mGoogleApiClient = GoogleApiClient.Builder(context!!)
//                    .enableAutoManage(context as FragmentActivity /* Activity */, this /* OnConnectionFailedListener */)
//                    .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
//                    .build()
//            }
//
//            val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
//            googleActivityLiveData.value = signInIntent
//
//        } else {
//            val accessToken = googleAccount.idToken
//            loginSocial("google", accessToken!!)
//        }

        if (mGoogleApiClient == null) {
            mGoogleApiClient = GoogleApiClient.Builder(context!!)
                .enableAutoManage(context as FragmentActivity /* Activity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build()
        }

        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        googleActivityLiveData.value = signInIntent
    }

    fun onAuthenGoogleSuccessful(accessToken: String, email: String){
        loginSocial("google", accessToken)
    }

    fun onFacebookClicked() {
        SimpleAuth.connectFacebook(emptyArray<String>().toList(), object : AuthCallback {
            override fun onSuccess(socialUser: SocialUser) {
                loginSocial("facebook", socialUser.accessToken)
            }

            override fun onError(error: Throwable?) {
                showToast(error?.message ?: "Error")
            }

            override fun onCancel() {
                showToast("Canceled")
            }
        })

    }

    fun onSignUpWithPhoneClicked() {
        showToast("Coming soon")
    }

    @SuppressLint("CheckResult")
    private fun loginSocial(provider: String, accessToken: String) {
        showProgressDialog()
        userInteractor.loginSocial(provider, accessToken, !BuildConfig.DEBUG)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ userObject -> loginSuccessfully(userObject) }, { error -> errorOccurs(error) })
    }

    private fun loginSuccessfully(userObject: UserObject) {
        hideProgressDialog()
        showToast("Successfully login")
        loginLiveData.value = userObject
    }

    private fun errorOccurs(error: Throwable) {
        hideProgressDialog()
        error.message?.let { showToast(ErrorParsing.parse(error)) }
    }

    override fun onConnectionFailed(p0: ConnectionResult) {

    }


}