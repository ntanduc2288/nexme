package com.nexme.data.model.request.login

import com.google.gson.annotations.SerializedName

class SignupPhoneRequestEntity {
    @SerializedName("user")
    var userPhoneEntity: UserPhoneEntity? = null
}