package com.example.ammymovie.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * homework com.example.ammymovie.experiments
 *
 * @author Amina
 * 28.07.2021
 */
class MainBroadcastReceiver : BroadcastReceiver() {
    // Урок 6
    // Подпишитесь на событие изменения и уведомляйте об этом пользователя.
    override fun onReceive(context: Context?, intent: Intent?) {
        val action = intent?.action
        action.let {
            if (Intent.ACTION_LOCALE_CHANGED == it) {
                StringBuilder().apply {
                    append("System message\n")
                    append("Action: ${intent?.action}")
                    toString().also {
                        Toast.makeText(context, this, Toast.LENGTH_LONG).show()
                    }
                }
            }
        }
    }

}
