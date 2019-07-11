package com.nexme.data.model.request.login

import com.google.gson.annotations.SerializedName

class UserEntity(
    @SerializedName("provider")
    var provider: String, @SerializedName("password")
    var password: String? = null, @SerializedName("uid")
    var uid: String? = null
)
