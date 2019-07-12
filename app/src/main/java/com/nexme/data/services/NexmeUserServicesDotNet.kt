package com.nexme.data.services

import com.nexme.data.services.Api.CLIENT_TOKEN
import com.nexme.data.model.request.login.LoginRequestEntity
import com.nexme.data.model.response.login.LoginResponseEntity
import com.nexme.data.services.Api.USER_AGENT
import io.reactivex.Observable
import retrofit2.http.*

// Host: service-dotnet.nexmeapp.com
interface NexmeUserServicesDotNet {
    @POST("Account/login")
    @Headers( CLIENT_TOKEN)
    fun login(@Body userRequestEntity: LoginRequestEntity): Observable<LoginResponseEntity>

}