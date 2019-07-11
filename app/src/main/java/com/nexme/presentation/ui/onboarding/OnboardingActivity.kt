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

const val RC_SIGN_IN = 123
class OnboardingActivity: BaseActivity(), GoogleApiClient.OnConnectionFailedListener {

    override fun getLayoutId() = R.layout.onboarding_activity
    private var onBoardingFragmentInstance: OnBoardingFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onBoardingFragmentInstance = OnBoardingFragment.newInstance()
        pushFragment(this, onBoardingFragmentInstance!!, false)
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

                if (!acct.idToken.isNullOrEmpty() && !mEmail.isNullOrEmpty()){
                    onBoardingFragmentInstance?.successfullyGettingGoogleAccount(acct.idToken!!, mEmail!!)
                }
            }
        }
    }




}