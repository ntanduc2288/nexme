package com.nexme.presentation.ui

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper

abstract class BaseActivity: AppCompatActivity() {

    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
}