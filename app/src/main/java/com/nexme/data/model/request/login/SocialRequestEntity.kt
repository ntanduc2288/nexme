package com.nexme.data.model.request.login

import com.google.gson.annotations.SerializedName

class SocialRequestEntity {
    @SerializedName("user")
    var userEntity: UserEntity? = null

    @SerializedName("accessToken")
    var accessToken: String? = null
}