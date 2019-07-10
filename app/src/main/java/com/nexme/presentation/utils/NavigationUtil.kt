package com.nexme.presentation.utils

import androidx.appcompat.app.AppCompatActivity
import com.nexme.R
import com.nexme.presentation.ui.BaseFragment


fun pushFragment(activity: AppCompatActivity, fragment: BaseFragment, addToBackStack: Boolean){
    val fragmentTransaction = activity.supportFragmentManager
        .beginTransaction()
    fragmentTransaction.replace(R.id.container, fragment)//R.id.content_frame is the layout you want to replace

    if (addToBackStack) {
        fragmentTransaction.addToBackStack(fragment.tag)
    }

    fragmentTransaction.commit()
}