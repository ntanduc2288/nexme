package com.nexme.presentation.manager

import com.nexme.presentation.ui.onboarding.login.UserObject
import com.nexme.presentation.utils.SharedPreferenceManager

object NexMeUserManager {

    var userObject: UserObject?
    get() = SharedPreferenceManager.userObject
    set(value) {SharedPreferenceManager.userObject = value}

}