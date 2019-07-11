package com.nexme.presentation.ui

import android.app.Application
import android.content.Context
import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class App: Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: App? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
        CalligraphyConfig.initDefault(
            CalligraphyConfig.Builder()
                .build()
        )
    }



}