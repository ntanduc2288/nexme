package com.nexme.presentation.utils

object AndroidUtil {
    fun isValidEmail(email: String): Boolean {
        return true
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

    fun isValidPhoneNumber(phoneNumber: String): Boolean {
        return phoneNumber.length >= 6
    }

    fun isValidCode(code: String): Boolean {
        return code.length == 4
    }

    fun isValidName(firstName: String, lastName: String): Boolean {
        return firstName.isNotEmpty() && lastName.isNotEmpty()
    }
}