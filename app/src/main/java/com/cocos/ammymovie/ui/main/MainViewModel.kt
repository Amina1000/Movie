package com.cocos.ammymovie.ui.main

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import com.cocos.ammymovie.App.Companion.getMovieDao
import com.cocos.ammymovie.domain.model.MovieListDTO
import com.cocos.ammymovie.domain.model.MovieSection
import com.cocos.ammymovie.domain.repository.MainRepository
import com.cocos.ammymovie.domain.repository.impls.MainRepositoryImpl
import com.cocos.ammymovie.domain.repository.impls.web.RemoteDataSource
import com.cocos.ammymovie.ui.common.AppState
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
            getUpcomingFromServer(lan, callBack(MovieSection.UPCOMING), 1, adultAdded)
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