package com.nexme.presentation.utils

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.nexme.R
import com.nexme.presentation.ui.App

object ErrorParsing {

    fun parse(error: Throwable): String{
        var errorMessage = App.applicationContext().getString(R.string.something_went_wrong)

        if (error is HttpException) {
            if (error.code() == 422){
                if (error.response()?.errorBody()?.string() == "false") {
                    errorMessage = App.applicationContext().getString(R.string.wrong_username_password)
                    return errorMessage
                }
            }
            errorMessage = error.message()
        }

        return errorMessage
    }
}