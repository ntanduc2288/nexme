package com.nexme.domain.nexme.login

import android.content.Context
import com.nexme.data.model.request.login.DeviceLoginEntity
import com.nexme.data.model.request.login.LoginRequestEntity
import com.nexme.data.model.request.login.UserEntity
import com.nexme.data.model.request.login.UserLoginEntity
import com.nexme.data.model.response.login.LoginResponseEntity
import com.nexme.data.services.Api
import com.nexme.domain.mapping
import com.nexme.presentation.manager.UserManager
import com.nexme.presentation.ui.onboarding.login.UserObject
import io.reactivex.Observable

class UserInteractorImpl : UserInteractor {

    override fun login(context: Context, password: String, uid: String, isProd: Boolean): Observable<UserObject> {

        val userAgent = Api.getUserAgent(context)

        return Api.nexmeUserServices.check(userAgent, uid)
            .flatMap { isUidExist -> Observable.just(initializeUserEntity(password, uid)) }
            .flatMap { loginRequestEntity -> Api.nexmeUserServicesDotNet.login(userAgent, loginRequestEntity) }
            .map { UserLoginEntity(DeviceLoginEntity("noToken", isProd))  }
            .flatMap { Api.nexmeUserServices.updateUILogin(userAgent, uid, it) }
            .map { loginResponse: LoginResponseEntity -> mapping(loginResponse) }
            .doOnNext { cacheUserData(it) }
    }

    private fun initializeUserEntity(password: String, uid: String): LoginRequestEntity {
        val userEntity = UserEntity("email", password, uid)
        return LoginRequestEntity(userEntity)
    }

    private fun cacheUserData(userObject: UserObject) {
        UserManager.userObject = userObject
    }

}