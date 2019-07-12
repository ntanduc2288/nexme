package com.nexme.domain.nexme.login

import android.content.Context
import com.nexme.data.model.response.twilio.PhoneResponseEntity
import com.nexme.data.services.Api
import com.nexme.presentation.ui.onboarding.login.UserObject
import io.reactivex.Observable
import retrofit2.http.Header

interface UserInteractor {
    fun login(context: Context, password: String, email: String, isProd: Boolean): Observable<UserObject>
    fun loginSocial(context: Context, provider: String, accessToken: String, isProd: Boolean): Observable<UserObject>
    fun requestPhoneVerification(context: Context, apiKey: String, countryCode: String, phoneNumber: String): Observable<PhoneResponseEntity>
    fun checkPhoneVerification(context: Context, apiKey: String, countryCode: String, phoneNumber: String, verificationCode: String): Observable<PhoneResponseEntity>
}