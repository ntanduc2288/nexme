package com.nexme.data.model.request.login

import com.google.gson.annotations.SerializedName

class UserEntity(
    @SerializedName("provider")
    var provider: String, @SerializedName("password")
    var password: String, @SerializedName("uid")
    var uid: String
)
