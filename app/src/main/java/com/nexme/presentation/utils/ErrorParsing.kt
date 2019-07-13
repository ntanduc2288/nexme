package com.nexme.presentation.utils

import com.jakewharton.retrofit2.adapter.rxjava2.HttpException
import com.nexme.R
import com.nexme.presentation.ui.NexMeApp

object ErrorParsing {

    fun parse(error: Throwable): String{
        var errorMessage = NexMeApp.applicationContext().getString(R.string.something_went_wrong)

        if (error is HttpException) {
            if (error.code() == 422){
                if (error.response()?.errorBody()?.string() == "false") {
                    errorMessage = NexMeApp.applicationContext().getString(R.string.email_already_in_use)
                    return errorMessage
                }
            }
            errorMessage = error.message()
        }

        return errorMessage
    }
}