package com.nexme.presentation.ui.onboarding.login

data class UserObject(val id: Int) {
    var agent_info: String? = null
    var agent_status: Int? = null
    var created_at: String? = null
    var device_token: String? = null
    var email: String? = null
    var firstname: String? = null
    var fullname: String? = null
    var image: String? = null
    var isActive: Boolean? = null
    var lastname: String? = null
    var nickName: String? = null
    var phone: String? = null
    var provider: String? = null
    var qualification: String? = null
    var token: String? = null
    var tokenExpiry: String? = null
    var uid: String ? = null
    var latitude: Double? = null
    var longitude: Double? = null
    var roleId: Int? = null

}