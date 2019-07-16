package com.nexme.data.model.response.login

data class LoginResponseEnitity2(
        val agent_info: String,
    val agent_status: Int,
    val created_at: String,
    val device_token: String,
    val email: String,
    val firstname: String,
    val fullname: String,
    val id: Int,
    val image: String,
    val isActive: Boolean,
    val isEmailConfirmed: Boolean,
    val isPhoneConfirmed: Boolean,
    val lastname: String,
    val location: String,
    val password: String,
    val passwordSalt: String,
    val phone: String,
    val provider: String,
    val qualification: String,
    val roles: String,
    val token: String,
    val uid: String,
    val updated_at: String
)