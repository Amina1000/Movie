package com.example.ammymovie.ui.main

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val mainRepositoryImpl: MainRepository = MainRepositoryImpl(
        RemoteDataSource(),
        getMovieDao(),
        Handler(Looper.getMainLooper())
    )
) : ViewModel() {

    private fun callBack(movieSection: MovieSection): Callback<MovieListDTO> {
        return object : Callback<MovieListDTO> {

            @Throws(IOException::class)
            override fun onResponse(call: Call<MovieListDTO>, response: Response<MovieListDTO>) {
                val serverResponse: MovieListDTO? = response.body()
                liveDataToObserve.postValue(
                    if (response.isSuccessful && serverResponse != null) {
                        checkResponse(serverResponse, movieSection)
                    } else {
                        AppState.Error(Throwable("Load error"))
                    }
                )
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

            getNowPlayingFromLocalStorage { repos ->
                if (repos.isNotEmpty()) {
                    liveDataToObserve.postValue(AppState.SuccessPlay(repos))
                } else {
                    getNowPlayingFromServer(lan, callBack(MovieSection.PLAY), 1)
                }
            }

            getUpcomingFromLocalStorage { repos ->
                if (repos.isNotEmpty()) {
                    liveDataToObserve.postValue(AppState.SuccessCome(repos))
                } else {
                    getUpcomingFromServer(lan, callBack(MovieSection.UPCOMING), 2)
                }
            }
        }
    }

    private fun checkResponse(serverResponse: MovieListDTO, movieSection: MovieSection): AppState {
        serverResponse.results?.forEach { it.section = movieSection.section }
        serverResponse.let { mainRepositoryImpl.saveEntity(it) }
        if (movieSection == MovieSection.UPCOMING) {
            return AppState.SuccessCome(serverResponse)
        } else {
            return AppState.SuccessPlay(serverResponse)
        }

    }
}