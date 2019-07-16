package com.nexme.presentation.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPreferenceManager {
    private lateinit var context: Context
    private lateinit var presf: SharedPreferences

    private val NEED_TO_SHOW_TOUR = "NEED_TO_SHOW_TOUR"

    fun initialize(context: Context) {
        this.context = context
        presf = context.getSharedPreferences("NexMe", 0)

    }

    var needToShowTour: Boolean
        get() = presf.getBoolean(NEED_TO_SHOW_TOUR, true)
        set(value) = presf.edit().putBoolean(NEED_TO_SHOW_TOUR, value).apply()
}