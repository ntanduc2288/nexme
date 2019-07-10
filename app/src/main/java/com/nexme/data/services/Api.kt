package com.nexme.data.services

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.nexme.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Api {

    const val CLIENT_TOKEN = "x-client-token: d7f11248-036f-4f92-bc91-70cd82de9255"
    const val USER_AGENT = "user-agent"

    private val STAGING_SERVICE_HOST = "https://service.nexmeapp.com"
    private val STAGING_SERVICE_DOTNET_HOST = "https://service-dotnet.nexmeapp.com"
    private val SERVICE_PATH = "/api/v1/"
    lateinit var nexmeUserServicesDotNet: NexmeUserServicesDotNet

    init {
        initializeHosts()
    }

    private fun initializeHosts() {
        val nexmeServiceAdapter = Retrofit.Builder()
            .baseUrl(STAGING_SERVICE_DOTNET_HOST + SERVICE_PATH)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        nexmeUserServicesDotNet = nexmeServiceAdapter.create(NexmeUserServicesDotNet::class.java)
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