package com.nexme.domain.nexme.login

import android.content.Context
import com.nexme.data.model.request.login.LoginRequestEntity
import com.nexme.data.model.request.login.UserEntity
import com.nexme.data.model.response.login.LoginResponseEntity
import com.nexme.data.services.Api
import com.nexme.domain.mapping
import com.nexme.presentation.ui.onboarding.login.UserObject
import io.reactivex.Observable

class UserInteractorImpl: UserInteractor {

    override fun login(context: Context, password: String, uid: String): Observable<UserObject> {
        val userEntity = UserEntity("email", password, uid)
        val loginRequestEntity = LoginRequestEntity(userEntity)
        return Api.nexmeUserServicesDotNet.login(Api.getUserAgent(context), loginRequestEntity)
            .map { loginResponse: LoginResponseEntity -> mapping(loginResponse) }
    }

}