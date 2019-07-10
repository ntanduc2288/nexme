package com.nexme.data.model.request.login

import com.google.gson.annotations.SerializedName

class LoginRequestEntity(
    @field:SerializedName("user")
    var user: UserEntity
)
