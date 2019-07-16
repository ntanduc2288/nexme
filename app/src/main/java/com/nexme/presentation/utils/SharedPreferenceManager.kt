package com.nexme.presentation.utils

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.nexme.presentation.ui.onboarding.login.UserObject

object SharedPreferenceManager {
    private lateinit var context: Context
    private lateinit var presf: SharedPreferences
    private val gson = Gson()

    private val NEED_TO_SHOW_TOUR = "NEED_TO_SHOW_TOUR"
    private val USER_OBJECT = "USER_OBJECT"

    fun initialize(context: Context) {
        this.context = context
        presf = context.getSharedPreferences("NexMe", 0)

    }

    var needToShowTour: Boolean
        get() = presf.getBoolean(NEED_TO_SHOW_TOUR, true)
        set(value) = presf.edit().putBoolean(NEED_TO_SHOW_TOUR, value).apply()

    var userObject: UserObject?
    get() {
        presf.getString(USER_OBJECT, null)?.let {
            return gson.fromJson(it, UserObject::class.java)
        }

        return null
    }
    set(value) {
        value?.let {
            val userObjectStr = gson.toJson(it)
            presf.edit().putString(USER_OBJECT, userObjectStr).apply()
        }
    }
}