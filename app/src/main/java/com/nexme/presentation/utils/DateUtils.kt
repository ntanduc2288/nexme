package com.nexme.presentation.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun convertSecondToHHMMSS(second: Int): String {
        val d = Date(second * 1000L)

        var formatStr = "HH:mm:ss"
        if (second < 3600) formatStr = "mm:ss"
        val df = SimpleDateFormat(formatStr)

        df.timeZone = TimeZone.getTimeZone("GMT")
        return df.format(d)
    }
}