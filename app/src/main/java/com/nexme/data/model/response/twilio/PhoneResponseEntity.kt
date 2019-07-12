package com.nexme.data.model.response.twilio

data class PhoneResponseEntity(
    var carrier: String,
    var is_cellphone: Boolean,
    var message: String,
    var seconds_to_expire: Int,
    var success: Boolean,
    var uuid: String
)