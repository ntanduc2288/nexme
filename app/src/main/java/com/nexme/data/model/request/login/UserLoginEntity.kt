package com.nexme.data.model.request.login

import com.google.gson.annotations.SerializedName

class UserLoginEntity(
    @SerializedName("v1_user")
    var deviceLoginEntity: DeviceLoginEntity
)