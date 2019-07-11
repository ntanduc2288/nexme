package com.nexme.presentation.ui.onboarding

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nexme.R
import com.nexme.presentation.ui.BaseLiveDataFragment
import com.nexme.presentation.ui.explore.MapsActivity
import com.nexme.presentation.ui.onboarding.login.LoginFragment
import com.nexme.presentation.ui.onboarding.login.UserObject
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
    }

    companion object {
        fun newInstance(): OnBoardingFragment {
            return OnBoardingFragment()
        }
    }
    override fun getLayoutId() = R.layout.onboarding_view

    override fun setupViews() {
        btnLogin.setOnClickListener { openLoginPage() }
        btnGoogle.setOnClickListener { onboardingViewModel.onGoogleClicked(context!!) }
        btnFacebook.setOnClickListener { onboardingViewModel.onFacebookClicked(context!!) }
        btnSigUpWithPhone.setOnClickListener { onboardingViewModel.onSignUpWithPhoneClicked() }
        btnSkip.setOnClickListener { openHomePage() }
    }

    private val loginSuccessfullyObserver = Observer<UserObject> {
        openHomePage()
    }

    private fun openHomePage() {
        startActivity(Intent(context, MapsActivity::class.java))
        getCurrentActivity().finish()
    }

    private fun openLoginPage(){
        pushFragment(getCurrentActivity(), LoginFragment.newInstance(), true)
    }

}