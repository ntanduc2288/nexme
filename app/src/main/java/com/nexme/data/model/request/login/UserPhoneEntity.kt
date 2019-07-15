package com.nexme.data.model.request.login

import com.google.gson.annotations.SerializedName

class UserPhoneEntity {
    @SerializedName("uid")
    var uid: String? = null
    @SerializedName("password_confirmation")
    var passwordConfirmation: String? = null
    @SerializedName("firstname")
    var firstname: String? = null
    @SerializedName("provider")
    var provider: String? = null
    @SerializedName("password")
    var password: String? = null
    @SerializedName("phone")
    var phone: String? = null
    @SerializedName("lastname")
    var lastname: String? = null
}