package com.nexme.domain.nexme.login

import android.content.Context
import com.nexme.presentation.ui.onboarding.login.UserObject
import io.reactivex.Observable

interface UserInteractor {
    fun login(context: Context, password: String, email: String, isProd: Boolean): Observable<UserObject>
}