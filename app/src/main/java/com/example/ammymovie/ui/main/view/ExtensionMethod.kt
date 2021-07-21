package com.example.ammymovie.ui.main.view

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.AppCompatImageView
import com.example.ammymovie.R
import com.google.android.material.snackbar.Snackbar
import java.util.*

/**
 * homework com.example.ammymovie.ui.main.view
 *
 * @author Amina
 * 16.07.2021
 */
const val DATE_TIME_FORMAT = "dd.MMM.yy"
const val AMMY_API_KEY = "e3a3441279eabd311384180ee90a3117"
//extension-функция, обработка изображения
fun AppCompatImageView.createImageFromResource() {
    setImageResource(R.drawable.cinematografico)
}

// добавила функцию расширения (extension-функцию) для класса Date.
fun Date.format(): String =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault())
            .format(this)
    } else {
        this.toString()
    }

//extension-функция для SnackBar
//2.Напишите дополнительные extension-функции для SnackBar
fun View.showSnackBar(
    text: String,
    actionText: String,
    action: (View) -> Unit,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    Snackbar.make(this, text, length).setAction(actionText, action).show()
}

fun View.showSnackBar(
    message: String,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    Snackbar
        .make(this, message, length)
        .show()
}

fun View.showSnackBar(
    messageId: Int,
    context: Context?,
    length: Int = Snackbar.LENGTH_INDEFINITE
) {
    context?.resources?.let {
        Snackbar
            .make(this, it.getString(messageId), length)
            .show()
    }
}

// Расширяем функционал вью для скрытия клавиатуры
fun View.hideKeyboard(): Boolean {
    try {
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
    } catch (ignored: RuntimeException) {
    }
    return false
}

// Расширяем функционал вью для отображения клавиатуры
fun View.showKeyboard() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    this.requestFocus()
    imm.showSoftInput(this, 0)
}

// Отображать вью в зависимости от условия
inline fun View.showIf(condition: () -> Boolean): View {
    when {
        visibility != View.VISIBLE && condition() -> {
            visibility = View.VISIBLE
        }
    }
    return this
}

inline fun View.hideIf(predicate: () -> Boolean): View {
    when {
        visibility != View.GONE && predicate() -> {
            visibility = View.GONE
        }
    }
    return this
}


