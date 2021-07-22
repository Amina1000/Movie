package com.example.ammymovie.ui.main.viewmodel

import android.os.Build
import android.os.Handler
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.ammymovie.BuildConfig
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

/**
 * homework com.example.ammymovie.ui.main.viewmodel
 *
 * @author Amina
 * 21.07.2021
 */
class MovieLoader(private val listener: MovieLoaderListener, private val movie: Any) {

    private val apiKey = BuildConfig.AMMY_API_KEY

    @RequiresApi(Build.VERSION_CODES.N)
    fun loadMovie() {
        try {
            val handler = Handler()
            val uri = getURL(movie)
            Thread {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.readTimeout = 10000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))
                    // преобразование ответа от сервера (JSON) в модель данных (WeatherDTO)
                    val movieDTO =Gson().fromJson(getLines(bufferedReader), MovieDTO::class.java)

                    handler.post { listener.onLoaded(movieDTO) }
                } catch (e: Exception) {
                    Log.e("", "Fail connection", e)
                    e.printStackTrace()
                    listener.onFailed(e)
                } finally {
                    urlConnection.disconnect()
                }
            }.start()
        } catch (e: MalformedURLException) {
            Log.e("", "Fail URI", e)
            e.printStackTrace()
            listener.onFailed(e)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

    private fun getURL(movie: Any) :URL{
        return when (movie) {
            is Int -> URL("https://api.themoviedb.org/3/movie/${movie}?api_key=${apiKey}&language=ru-RU")
            is String -> URL("https://api.themoviedb.org/3/search/movie?api_key=${apiKey}&query=${movie}")
            else -> URL("https://api.themoviedb.org/3/movie/now_playing?api_key=${apiKey}&language=ru-RU")
        }
    }
}

interface MovieLoaderListener {
    fun onLoaded(movieDTO: MovieDTO)
    fun onFailed(throwable: Throwable)
}