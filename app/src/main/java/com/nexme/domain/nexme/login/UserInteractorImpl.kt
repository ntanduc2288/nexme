package com.nexme.domain.nexme.login

import com.nexme.data.model.request.login.*
import com.nexme.data.model.response.login.LoginResponseEntity
import com.nexme.data.model.response.twilio.PhoneCodeResponseEntity
import com.nexme.data.model.response.twilio.PhoneResponseEntity
import com.nexme.data.services.NexMeApi
import com.nexme.domain.mapping
import com.nexme.presentation.manager.NexMeUserManager
import com.nexme.presentation.ui.onboarding.login.UserObject
import io.reactivex.Observable

class UserInteractorImpl : UserInteractor {

    override fun login(password: String, email: String, isProd: Boolean): Observable<UserObject> {
        return NexMeApi.nexmeUserServices.check(email)
            .flatMap { Observable.just(initializeUserEntity(password, email)) }
            .flatMap { loginRequestEntity -> NexMeApi.nexmeUserServicesDotNet.login(loginRequestEntity) }
            .map { UserLoginEntity(DeviceLoginEntity("noToken", isProd))  }
            .flatMap { NexMeApi.nexmeUserServices.updateUILogin(email, it) }
            .map { loginResponse: LoginResponseEntity -> mapping(loginResponse) }
            .doOnNext { cacheUserData(it) }
    }

    override fun signupPhone(
        email: String,
        password: String,
        phone: String,
        firstName: String,
        lastName: String,
        isProd: Boolean
    ): Observable<UserObject> {
        return Observable.just(initializePhoneUserSignupEntity(email, password, phone, firstName, lastName))
            .flatMap { loginPhoneRequestEntity -> NexMeApi.nexmeUserServicesDotNet.loginViaPhone(loginPhoneRequestEntity) }
            .map { UserLoginEntity(DeviceLoginEntity("noToken", isProd))  }
            .flatMap { NexMeApi.nexmeUserServices.updateUILogin(email, it) }
            .map { loginResponse: LoginResponseEntity -> mapping(loginResponse) }
            .doOnNext { cacheUserData(it) }
    }

    override fun loginSocial(provider: String, accessToken: String, isProd: Boolean): Observable<UserObject> {
        return Observable.just(initializeSocialUserEntity(provider, accessToken))
            .flatMap { loginRequestEntity -> NexMeApi.nexmeUserServicesDotNet.login(loginRequestEntity) }
            .map { initializeSocialUserLoginEntity(isProd, it.email)  }
            .flatMap { NexMeApi.nexmeUserServices.updateUILogin(it.first, it.second) }
            .map { loginResponse: LoginResponseEntity -> mapping(loginResponse) }
            .doOnNext { cacheUserData(it) }
    }

    override fun requestPhoneVerification(
        apiKey: String,
        countryCode: String,
        phoneNumber: String
    ): Observable<PhoneResponseEntity> {
        return NexMeApi.twilioService.requestPhoneVerify(apiKey, "sms", countryCode, phoneNumber)
    }

    override fun checkPhoneVerification(
        apiKey: String,
        countryCode: String,
        phoneNumber: String,
        verificationCode: String
    ): Observable<PhoneCodeResponseEntity> {
        return NexMeApi.twilioService.checkPhoneVerify(apiKey, countryCode, phoneNumber, verificationCode)
    }

    override fun checkUID(uid: String): Observable<Boolean> {
        return NexMeApi.nexmeUserServices.check(uid)
    }

    private fun initializeUserEntity(password: String, uid: String): LoginRequestEntity {
        val userEntity = UserEntity("email", password, uid)
        return LoginRequestEntity(userEntity)
    }

    private fun initializeSocialUserEntity(provider: String, accessToken: String): LoginRequestEntity {
        val userEntity = UserEntity(provider)
        return LoginRequestEntity(userEntity, accessToken)
    }

    private fun initializeSocialUserLoginEntity(isProd: Boolean, uid: String): Pair<String, UserLoginEntity> {
        val userLoginEntity = UserLoginEntity(DeviceLoginEntity("noToken", isProd))
        return Pair(uid, userLoginEntity)
    }

    private fun initializePhoneUserSignupEntity(uid: String,
                                                password: String,
                                                phone: String,
                                                firstName: String,
                                                lastName: String): SignupPhoneRequestEntity {
        val loginPhoneRequestEntity = SignupPhoneRequestEntity()
        val userPhoneEntity = UserPhoneEntity()
        userPhoneEntity.uid = uid
        userPhoneEntity.firstname = firstName
        userPhoneEntity.lastname = lastName
        userPhoneEntity.password = password
        userPhoneEntity.passwordConfirmation = password
        userPhoneEntity.phone = phone
        userPhoneEntity.provider = "email"

        loginPhoneRequestEntity.userPhoneEntity = userPhoneEntity

        return loginPhoneRequestEntity
    }

    private fun cacheUserData(userObject: UserObject) {
        NexMeUserManager.userObject = userObject
    }

}