package com.nexme.data.services

import com.nexme.data.model.response.twilio.PhoneCodeResponseEntity
import com.nexme.data.model.response.twilio.PhoneResponseEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface TwilioService {
    @POST("protected/json/phones/verification/start")
    fun requestPhoneVerify(@Query("api_key") apiKey: String, @Query("via") via: String, @Query("country_code") countryCode: String, @Query("phone_number") phoneNumber: String ): Observable<PhoneResponseEntity>

    @GET("protected/json/phones/verification/check")
    fun checkPhoneVerify(@Query("api_key") apiKey: String, @Query("country_code") countryCode: String, @Query("phone_number") phoneNumber: String, @Query("verification_code") verificationCode: String ): Observable<PhoneCodeResponseEntity>
}