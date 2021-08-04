package com.example.ammymovie.service

import android.app.IntentService
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.example.ammymovie.BuildConfig
import com.example.ammymovie.domain.model.MovieListDTO
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

/**
 * homework com.example.ammymovie.experiments
 *
 * @author Amina
 * 27.07.2021
 */
const val MAIN_SERVICE_EXTRA = "MainServiceExtra"
const val MAIN_LOAD_EXTRA = "MainLoadExtra"

@RequiresApi(Build.VERSION_CODES.N)
class MainService(name: String? = "test") : IntentService(name) {

    private val broadcastIntent = Intent(MAIN_SERVICE_EXTRA)

    override fun onHandleIntent(intent: Intent?) {

        val requestLink = "https://api.themoviedb.org/3/movie/now_playing?api_key=${BuildConfig.AMMY_API_KEY}&language=ru-RU"
        loadListMovie(requestLink)
    }

    private fun loadListMovie(requestLink:String) {
        try {
            val uri = URL(requestLink)
            Thread {
                lateinit var urlConnection: HttpsURLConnection
                try {
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.readTimeout = 10000
                    val bufferedReader =
                        BufferedReader(InputStreamReader(urlConnection.inputStream))
                    // преобразование ответа от сервера (JSON) в модель данных (WeatherDTO)
                    val movieListDTO = Gson().fromJson(getLines(bufferedReader), MovieListDTO::class.java)
                    onResponse(movieListDTO)

                } catch (e: Exception) {
                    Log.e("", "Fail connection", e)
                    e.printStackTrace()
                } finally {
                    urlConnection.disconnect()
                }
            }.start()
        } catch (e: MalformedURLException) {
            Log.e("", "Fail URI", e)
            e.printStackTrace()
        }
    }

    private fun onResponse(movieListDTO: MovieListDTO) {
        val results = movieListDTO.results
        results.let{
            broadcastIntent.putParcelableArrayListExtra(MAIN_LOAD_EXTRA, results)
            LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
        }
    }
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

}