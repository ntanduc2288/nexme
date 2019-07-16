package com.nexme.presentation.ui.onboarding.login

import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.nexme.R
import com.nexme.presentation.ui.BaseLiveDataFragment
import com.nexme.presentation.ui.NexMeApp
import com.nexme.presentation.utils.AndroidUtil
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

        btnSkip.setOnClickListener { gotoNextPageAfterSignUpOrLogin() }
        btnBack.setOnClickListener { getCurrentActivity().onBackPressed() }
        btnSignUp.setOnClickListener { getCurrentActivity().onBackPressed() }
        btnLogin.setOnClickListener { loginViewModel.onLoginClicked(edtPassword.text.toString().trim(), edtEmail.text.toString().trim()) }
        lblForgotPassword.setOnClickListener { Toast.makeText(NexMeApp.applicationContext(), "Coming soon", Toast.LENGTH_SHORT).show() }

        edtEmail.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                btnLogin.isEnabled = shouldEnableLoginButton()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

        edtPassword.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
                btnLogin.isEnabled = shouldEnableLoginButton()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })

    }

    private fun shouldEnableLoginButton(): Boolean {
        return AndroidUtil.isValidEmail(edtEmail.text.toString().trim()) && AndroidUtil.isValidPassword(edtPassword.text.toString().trim())
    }

    private val loginSuccessfullyObserver = Observer<UserObject> {
        gotoNextPageAfterSignUpOrLogin()
    }

}