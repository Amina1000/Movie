package com.example.ammymovie.ui.main

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import com.example.ammymovie.App.Companion.getMovieDao
import com.example.ammymovie.domain.model.MovieListDTO
import com.example.ammymovie.domain.model.MovieSection
import com.example.ammymovie.domain.repository.MainRepository
import com.example.ammymovie.domain.repository.impls.MainRepositoryImpl
import com.example.ammymovie.domain.repository.impls.web.RemoteDataSource
import com.example.ammymovie.ui.common.AppState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MainViewModel(
    app: Application,
    private val mainRepositoryImpl: MainRepository
) : ViewModel() {
    val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val preferenceManager = PreferenceManager.getDefaultSharedPreferences(app)
    private var adultAdded = !preferenceManager.getBoolean("adultClick", true)
    private fun callBack(movieSection: MovieSection): Callback<MovieListDTO> {
        return object : Callback<MovieListDTO> {

            @Throws(IOException::class)
            override fun onResponse(call: Call<MovieListDTO>, response: Response<MovieListDTO>) {
                val serverResponse: MovieListDTO? = response.body()
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse, movieSection)
                } else {
                    AppState.Error(Throwable("Load error"))
                }
            }

            override fun onFailure(call: Call<MovieListDTO>, t: Throwable) {
                liveDataToObserve.postValue(
                    AppState.Error(
                        Throwable(
                            t.message ?: "Load error"
                        )
                    )
                )
            }
        }
    }

    fun getDataFromLocalSource(lan: String) {
        liveDataToObserve.value = AppState.Loading
        with(mainRepositoryImpl) {
            getNowPlayingFromServer(lan, callBack(MovieSection.PLAY), 1, adultAdded)
            getUpcomingFromServer(lan, callBack(MovieSection.UPCOMING), 2, adultAdded)
            getMovieFromLocalStorage(
                { repos ->
                    liveDataToObserve.postValue(AppState.Success(repos))
                },
                adultAdded
            )
        }
    }

    private fun checkResponse(serverResponse: MovieListDTO, movieSection: MovieSection) {
        serverResponse.results?.forEach { it.section = movieSection.section }
        serverResponse.let { mainRepositoryImpl.saveEntity(it) }
    }
}