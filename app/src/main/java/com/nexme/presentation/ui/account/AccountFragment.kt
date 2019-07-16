package com.nexme.presentation.ui.account

import androidx.lifecycle.ViewModelProviders
import com.nexme.R
import com.nexme.presentation.ui.BaseLiveDataFragment

class AccountFragment: BaseLiveDataFragment() {
    private lateinit var accountViewModel: AccountViewModel

    companion object {
        fun newInstance(): AccountFragment {
            return AccountFragment()
        }
    }

    override fun registerViewModels() {
        accountViewModel = ViewModelProviders.of(this).get(AccountViewModel::class.java)
    }

    override fun getCurrentViewModel() = accountViewModel

    override fun subscribeObservers() {
    }

    override fun getLayoutId() = R.layout.acount_layout

    override fun setupViews() {
    }
}