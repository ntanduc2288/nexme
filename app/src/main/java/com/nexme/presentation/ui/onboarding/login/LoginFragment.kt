package com.nexme.presentation.ui.onboarding.login

import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nexme.R
import com.nexme.data.services.Api
import com.nexme.data.model.request.login.UserEntity
import com.nexme.data.model.request.login.LoginRequestEntity
import com.nexme.presentation.ui.BaseFragment
import com.nexme.presentation.ui.BaseLiveDataFragment
import com.nexme.presentation.ui.BaseViewModel
import com.nexme.presentation.ui.explore.MapsActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.login_view.*

class LoginFragment: BaseLiveDataFragment() {
    lateinit var loginViewModel: LoginViewModel
    override fun registerViewModels() {
        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)
    }

    override fun getCurrentViewModel() = loginViewModel

    override fun subscribeObservers() {
        loginViewModel.loginLiveData.observe(this, loginSuccessfullyObserver)
    }

    companion object {
        fun newInstance(): LoginFragment{
            return LoginFragment()
        }
    }
    override fun getLayoutId() = R.layout.login_view

    override fun setupViews() {

        edtEmail.setText("ntanduc2288@gmail.com")
        edtPassword.setText("NguyenTanDuc2288")

        btnSkip.setOnClickListener { openHomePage() }
        btnBack.setOnClickListener { getCurrentActivity().onBackPressed() }
        btnLogin.setOnClickListener { loginViewModel.onLoginClicked(context!!, edtPassword.text.toString().trim(), edtEmail.text.toString().trim()) }

    }

    private val loginSuccessfullyObserver = Observer<UserObject> {
        openHomePage()
    }

    private fun openHomePage() {
        startActivity(Intent(context, MapsActivity::class.java))
        getCurrentActivity().finish()
    }
}