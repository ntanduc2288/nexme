package com.nexme.domain.nexme.login

import com.nexme.data.model.response.twilio.PhoneCodeResponseEntity
import com.nexme.data.model.response.twilio.PhoneResponseEntity
import com.nexme.presentation.ui.onboarding.login.UserObject
import io.reactivex.Observable

interface UserInteractor {
    fun login(password: String, email: String, isProd: Boolean): Observable<UserObject>
    fun loginSocial(provider: String, accessToken: String, isProd: Boolean): Observable<UserObject>
    fun signupPhone(email: String, password: String, phone: String, firstName: String, lastName: String, isProd: Boolean): Observable<UserObject>
    fun requestPhoneVerification(apiKey: String, countryCode: String, phoneNumber: String): Observable<PhoneResponseEntity>
    fun checkPhoneVerification(apiKey: String, countryCode: String, phoneNumber: String, verificationCode: String): Observable<PhoneCodeResponseEntity>
    fun checkUID(uid: String): Observable<Boolean>
}