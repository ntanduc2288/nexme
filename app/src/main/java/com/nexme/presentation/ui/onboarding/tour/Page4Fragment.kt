package com.nexme.presentation.ui.onboarding.tour

import android.Manifest
import android.content.Intent
import com.nexme.R
import com.nexme.presentation.ui.BaseFragment
import kotlinx.android.synthetic.main.page4_fragment.*
import pub.devrel.easypermissions.EasyPermissions

class Page4Fragment: BaseFragment() {
    companion object{
        fun newInstance():Page4Fragment{
            return Page4Fragment()
        }
    }
    override fun getLayoutId() = R.layout.page4_fragment

    override fun setupViews() {
        btnEnable.setOnClickListener {

//            EasyPermissions.requestPermissions(getCurrentActivity(), getString(R.string.location_required),
//                1, Manifest.permission.ACCESS_NOTIFICATION_POLICY
//            )
//
//            startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS").addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }
}