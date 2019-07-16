package com.nexme.presentation.ui.explore

import androidx.lifecycle.ViewModelProviders
import com.nexme.R
import com.nexme.presentation.ui.BaseLiveDataFragment

class ServicesFragment: BaseLiveDataFragment() {
    private lateinit var servicesViewModel: ServicesViewModel

    companion object {
        fun newInstance(): ServicesFragment {
            return ServicesFragment()
        }
    }

    override fun registerViewModels() {
        servicesViewModel = ViewModelProviders.of(this).get(ServicesViewModel::class.java)
    }

    override fun getCurrentViewModel() = servicesViewModel

    override fun subscribeObservers() {
    }

    override fun getLayoutId() = R.layout.services_layout

    override fun setupViews() {
    }
}