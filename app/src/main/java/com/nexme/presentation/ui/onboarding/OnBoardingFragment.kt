package com.nexme.presentation.ui.onboarding

import android.content.Intent
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nexme.R
import com.nexme.presentation.model.SignupObject
import com.nexme.presentation.ui.BaseLiveDataFragment
import com.nexme.presentation.ui.explore.MapsActivity
import com.nexme.presentation.ui.onboarding.login.LoginFragment
import com.nexme.presentation.ui.onboarding.login.UserObject
import com.nexme.presentation.ui.onboarding.signupcode.SignupCodeFragment
import com.nexme.presentation.ui.onboarding.signupemail.SignupEmailFragment
import com.nexme.presentation.ui.onboarding.signupmobile.SignupMobileFragment
import com.nexme.presentation.ui.onboarding.signupname.SignupNameFragment
import com.nexme.presentation.ui.onboarding.signuppassword.SignupPasswordFragment
import com.nexme.presentation.ui.onboarding.tour.TourFragment
import com.nexme.presentation.utils.pushFragment
import kotlinx.android.synthetic.main.onboarding_view.*

class OnBoardingFragment: BaseLiveDataFragment() {


    private lateinit var onboardingViewModel: OnboardingViewModel

    override fun registerViewModels() {
        onboardingViewModel = ViewModelProviders.of(this).get(OnboardingViewModel::class.java)
    }

    override fun getCurrentViewModel() = onboardingViewModel

    override fun subscribeObservers() {
        onboardingViewModel.loginLiveData.observe(this, loginSuccessfullyObserver)
        onboardingViewModel.googleActivityLiveData.observe(this, openGoogleActivityObserver)
    }

    companion object {
        fun newInstance(): OnBoardingFragment {
            return OnBoardingFragment()
        }
    }
    override fun getLayoutId() = R.layout.onboarding_view

    override fun setupViews() {
        btnLogin.setOnClickListener { openLoginPage() }
        btnGoogle.setOnClickListener {

            onboardingViewModel.onGoogleClicked(activity!!)
//            gPlusSignIn()
        }

        btnFacebook.setOnClickListener { onboardingViewModel.onFacebookClicked() }
        btnSigUpWithPhone.setOnClickListener { openSignupMobilePage() }
        btnSkip.setOnClickListener { openHomePage() }
    }

    private val loginSuccessfullyObserver = Observer<UserObject> {
        openHomePage()
    }

    private val openGoogleActivityObserver = Observer<Intent> {
        activity?.startActivityForResult(it, RC_SIGN_IN)
    }


    private fun openLoginPage(){
        pushFragment(getCurrentActivity(), LoginFragment.newInstance(), true)
    }

    private fun openSignupMobilePage(){
        pushFragment(getCurrentActivity(), SignupMobileFragment.newInstance(), true)


        // Test sign up code
//        val signupObject = SignupObject()
//        signupObject.phoneNumber = "2068223780"
//        signupObject.secondToExpire = 10
//        signupObject.countryCode = "1"
//        pushFragment(getCurrentActivity(), SignupCodeFragment.newInstance(signupObject), true)
    }

    fun successfullyGettingGoogleAccount(accessToken: String, email: String){
        onboardingViewModel.onAuthenGoogleSuccessful(accessToken, email)
    }

}