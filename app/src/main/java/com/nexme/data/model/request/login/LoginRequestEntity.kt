package com.nexme.data.model.request.login

import com.facebook.AccessToken
import com.google.gson.annotations.SerializedName

class LoginRequestEntity(
    @SerializedName("user")
    var user: UserEntity,
    @SerializedName("accessToken")
    var accessToken: String? = null

)
