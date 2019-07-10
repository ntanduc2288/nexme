package com.nexme.domain

import com.nexme.data.model.response.login.LoginResponseEntity
import com.nexme.presentation.ui.onboarding.login.UserObject

fun mapping(loginResponseEntity: LoginResponseEntity): UserObject {
    val userObject = UserObject(loginResponseEntity.id)
    userObject.uid = loginResponseEntity.uid
    userObject.email = loginResponseEntity.email
    userObject.firstname = loginResponseEntity.firstname
    userObject.lastname = loginResponseEntity.lastname
    userObject.fullname = loginResponseEntity.fullname
    userObject.agent_info = loginResponseEntity.agent_info
    userObject.isActive = loginResponseEntity.isActive
    userObject.isEmailConfirmed = loginResponseEntity.isEmailConfirmed
    userObject.isPhoneConfirmed = loginResponseEntity.isPhoneConfirmed
    userObject.phone = loginResponseEntity.phone
    userObject.roles = loginResponseEntity.roles
    userObject.token = loginResponseEntity.token
    userObject.image = loginResponseEntity.image
    userObject.device_token = loginResponseEntity.device_token
    userObject.location = loginResponseEntity.location
    userObject.qualification = loginResponseEntity.qualification
    return userObject
}