package com.nexme.data.model.request.login

import com.google.gson.annotations.SerializedName

class DeviceLoginEntity(
    @SerializedName("device_token")
    var device_token: String,
    @SerializedName("device_is_prod")
    var device_is_prod: Boolean
) {
}