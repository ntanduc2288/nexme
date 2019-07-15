package com.nexme.data.services

import com.nexme.data.model.request.login.SignupPhoneRequestEntity
import com.nexme.data.services.NexMeApi.CLIENT_TOKEN
import com.nexme.data.model.request.login.LoginRequestEntity
import com.nexme.data.model.response.login.LoginResponseEntity
import io.reactivex.Observable
import retrofit2.http.*

// Host: service-dotnet.nexmeapp.com
interface NexmeUserServicesDotNet {
    @POST("Account/login")
    @Headers( CLIENT_TOKEN)
    fun login(@Body userRequestEntity: LoginRequestEntity): Observable<LoginResponseEntity>

    @POST("Account/login")
    @Headers( CLIENT_TOKEN)
    fun loginViaPhone(@Body loginPhoneRequestEntity: SignupPhoneRequestEntity): Observable<LoginResponseEntity>

    @POST("Account/AuthenticateWithToken")
    @Headers( CLIENT_TOKEN)
    fun loginGoogle(@Body userRequestEntity: LoginRequestEntity): Observable<LoginResponseEntity>

}