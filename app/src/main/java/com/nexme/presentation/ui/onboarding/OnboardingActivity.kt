package com.nexme.presentation.ui.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.GoogleAuthUtil
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.nexme.R
import com.nexme.presentation.ui.BaseActivity
import com.nexme.presentation.utils.pushFragment


class OnboardingActivity: BaseActivity(), GoogleApiClient.OnConnectionFailedListener {
    val RC_SIGN_IN = 123
    override fun getLayoutId() = R.layout.onboarding_activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pushFragment(this, OnBoardingFragment.newInstance(), false)
    }

    fun gPlusSignIn() {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken("236473905821-fets70bkgbu22sj2p3gkbndd121s6hup.apps.googleusercontent.com")
            .requestProfile()
            .requestEmail()
            .build()

        val mGoogleApiClient = GoogleApiClient.Builder(this@OnboardingActivity)
            .enableAutoManage(this@OnboardingActivity /* Activity */, this /* OnConnectionFailedListener */)
            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
            .build()

        val signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient)
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }


    override fun onConnectionFailed(p0: ConnectionResult) {
        Toast.makeText(this@OnboardingActivity, p0.errorMessage, Toast.LENGTH_SHORT).show()
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == RC_SIGN_IN) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                val acct = result.signInAccount
                //get user's email
                val mEmail = acct!!.email

                //get user's full name
                val mFullName = acct.displayName

                val gPlusID = acct.id
            }
        }
    }




}