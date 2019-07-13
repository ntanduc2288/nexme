package com.nexme.presentation.ui.onboarding.tour

import com.nexme.R
import com.nexme.presentation.ui.BaseFragment
import kotlinx.android.synthetic.main.page3_fragment.*
import android.Manifest.permission.ACCESS_FINE_LOCATION
import pub.devrel.easypermissions.EasyPermissions


class Page3Fragment: BaseFragment() {
    companion object{
        fun newInstance():Page3Fragment{
            return Page3Fragment()
        }
    }
    override fun getLayoutId() = R.layout.page3_fragment

    override fun setupViews() {

        btnEnable.setOnClickListener {

            EasyPermissions.requestPermissions(getCurrentActivity(), getString(R.string.location_required),
                1, ACCESS_FINE_LOCATION)
        }

    }
}