package com.example.ammymovie.utils

import android.icu.text.SimpleDateFormat
import android.os.Build
import java.util.*

/**
 * homework com.example.ammymovie.utils
 *
 * @author Amina
 * 23.07.2021
 */
const val DATE_TIME_FORMAT = "dd.MMM.yy"
// добавила функцию расширения (extension-функцию) для класса Date.
fun Date.format(): String =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault())
            .format(this)
    } else {
        this.toString()
    }