package com.nexme.presentation.ui

import android.app.Application
import android.content.Context
import com.nexme.R
import com.nexme.presentation.utils.SharedPreferenceManager
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class NexMeApp: Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: NexMeApp? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()

        initFonts()

        initSharedPreference()

    }

    private fun initSharedPreference() {
        SharedPreferenceManager.initialize(this)
    }

    private fun initFonts() {
        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .setDefaultFontPath(getString(R.string.montserrat_regular))
                .build()
        )
    }


}