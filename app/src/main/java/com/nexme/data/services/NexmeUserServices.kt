package com.nexme.data.services

import com.nexme.data.services.Api.CLIENT_TOKEN
import com.nexme.data.model.request.login.LoginRequestEntity
import com.nexme.data.model.request.login.UserLoginEntity
import com.nexme.data.model.response.login.LoginResponseEntity
import com.nexme.data.services.Api.UID
import com.nexme.data.services.Api.USER_AGENT
import io.reactivex.Observable
import retrofit2.http.*

// Host: service.nexmeapp.com
interface NexmeUserServices {
    @GET("users/check")
    @Headers( CLIENT_TOKEN)
    fun check(@Header(USER_AGENT) userAgent: String, @Header(UID) uid: String): Observable<Boolean>

    @PUT("users/0")
    @Headers( CLIENT_TOKEN)
    fun updateUILogin(@Header(USER_AGENT) userAgent: String, @Header(UID) uid: String, @Body userLoginEntity: UserLoginEntity): Observable<LoginResponseEntity>

}