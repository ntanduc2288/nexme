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
    userObject.isActive = loginResponseEntity.active
    userObject.phone = loginResponseEntity.phone
    userObject.token = loginResponseEntity.token
    userObject.tokenExpiry = loginResponseEntity.token_expiry
    userObject.image = loginResponseEntity.image
    userObject.device_token = loginResponseEntity.device_token
    userObject.latitude = loginResponseEntity.latitude
    userObject.longitude = loginResponseEntity.longitude
    userObject.qualification = loginResponseEntity.qualification
    userObject.roleId = loginResponseEntity.role_id
    userObject.nickName = loginResponseEntity.nickname
    userObject.agent_status = loginResponseEntity.status
    userObject.provider = loginResponseEntity.provider
    userObject.roles = loginResponseEntity.roles
    userObject.created_at = loginResponseEntity.created_at
    return userObject
}