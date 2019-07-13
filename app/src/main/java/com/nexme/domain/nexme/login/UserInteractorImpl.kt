package com.nexme.domain.nexme.login

import com.nexme.data.model.request.login.DeviceLoginEntity
import com.nexme.data.model.request.login.LoginRequestEntity
import com.nexme.data.model.request.login.UserEntity
import com.nexme.data.model.request.login.UserLoginEntity
import com.nexme.data.model.response.login.LoginResponseEntity
import com.nexme.data.model.response.twilio.PhoneResponseEntity
import com.nexme.data.services.NexMeApi
import com.nexme.domain.mapping
import com.nexme.presentation.manager.UserManager
import com.nexme.presentation.ui.onboarding.login.UserObject
import io.reactivex.Observable

class UserInteractorImpl : UserInteractor {

    override fun login(password: String, uid: String, isProd: Boolean): Observable<UserObject> {
        return NexMeApi.nexmeUserServices.check(uid)
            .flatMap { isUidExist -> Observable.just(initializeUserEntity(password, uid)) }
            .flatMap { loginRequestEntity -> NexMeApi.nexmeUserServicesDotNet.login(loginRequestEntity) }
            .map { UserLoginEntity(DeviceLoginEntity("noToken", isProd))  }
            .flatMap { NexMeApi.nexmeUserServices.updateUILogin(uid, it) }
            .map { loginResponse: LoginResponseEntity -> mapping(loginResponse) }
            .doOnNext { cacheUserData(it) }
    }

    override fun loginSocial(provider: String, accessToken: String, isProd: Boolean): Observable<UserObject> {
        return Observable.just(initializeSocialUserEntity(provider, accessToken))
            .flatMap { loginRequestEntity -> NexMeApi.nexmeUserServicesDotNet.login(loginRequestEntity) }
//            .flatMap { loginRequestEntity -> NexMeApi.nexmeUserServicesDotNet.loginGoogle(loginRequestEntity) }
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
    ): Observable<PhoneResponseEntity> {
        return NexMeApi.twilioService.checkPhoneVerify(apiKey, "sms", countryCode, phoneNumber, verificationCode)
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

    private fun cacheUserData(userObject: UserObject) {
        UserManager.userObject = userObject
    }

}