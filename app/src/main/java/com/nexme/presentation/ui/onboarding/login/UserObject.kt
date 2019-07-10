package com.nexme.presentation.ui.onboarding.login

data class UserObject(val id: Int) {
    var agent_info: String? = null
    var agent_status: Int = 0
    var created_at: String? = null
    var device_token: String? = null
    var email: String? = null
    var firstname: String? = null
    var fullname: String? = null
    var image: String? = null
    var isActive: Boolean? = null
    var isEmailConfirmed: Boolean = false
    var isPhoneConfirmed: Boolean = false
    var lastname: String? = null
    var location: String? = null
    var password: String? = null
    var passwordSalt: String? = null
    var phone: String? = null
    var provider: String? = null
    var qualification: String? = null
    var roles: String? = null
    var token: String? = null
    var uid: String ? = null

}