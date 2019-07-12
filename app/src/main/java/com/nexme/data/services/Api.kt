package com.nexme.data.services

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.nexme.presentation.ui.App
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import okhttp3.OkHttpClient



object Api {

    const val CLIENT_TOKEN = "x-client-token: d7f11248-036f-4f92-bc91-70cd82de9255"
    const val USER_AGENT = "user-agent"
    const val UID = "uid"

    private val STAGING_SERVICE_HOST = "https://service.nexmeapp.com"
    private val STAGING_SERVICE_DOTNET_HOST = "https://service-dotnet.nexmeapp.com"
    private val TWILIO_SERVICE_HOST = "https://api.authy.com/"
    private val SERVICE_PATH = "/api/v1/"
    lateinit var nexmeUserServicesDotNet: NexmeUserServicesDotNet
    lateinit var nexmeUserServices: NexmeUserServices
    lateinit var twilioService: TwilioService

    init {
        initializeHosts()
    }

    private fun initializeHosts() {
        val httpClient = OkHttpClient.Builder()
        httpClient.addNetworkInterceptor(AddHeaderInterceptor())
        val okHttpClient = httpClient.build()

        val nexmeServiceDotnetAdapter = Retrofit.Builder()
            .baseUrl(STAGING_SERVICE_DOTNET_HOST + SERVICE_PATH)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        val nexmeServiceAdapter = Retrofit.Builder()
            .baseUrl(STAGING_SERVICE_HOST + SERVICE_PATH)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        val twilioServicesAdapter = Retrofit.Builder()
            .baseUrl(TWILIO_SERVICE_HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

        nexmeUserServicesDotNet = nexmeServiceDotnetAdapter.create(NexmeUserServicesDotNet::class.java)
        nexmeUserServices = nexmeServiceAdapter.create(NexmeUserServices::class.java)
        twilioService = twilioServicesAdapter.create(TwilioService::class.java)
    }

    fun getUserAgent(context: Context): String{
        val info = context.packageManager.getPackageInfo(context.packageName, 0)
        val versionName = info.versionName
        val stringBuilder = StringBuilder()
        stringBuilder.append("AndroidApp")
            .append("/")
            .append(versionName)

        return stringBuilder.toString()
    }

}

class AddHeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val builder = chain.request().newBuilder()
        builder.addHeader(Api.USER_AGENT, Api.getUserAgent(App.applicationContext()))

        return chain.proceed(builder.build())
    }
}