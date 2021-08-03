package com.example.ammymovie.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ammymovie.domain.model.MovieListDTO
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
    private val mainRepositoryImpl: MainRepository = MainRepositoryImpl(RemoteDataSource())
) : ViewModel() {

    private val callBackPlay = object : Callback<MovieListDTO> {

        @Throws(IOException::class)
        override fun onResponse(call: Call<MovieListDTO>, response: Response<MovieListDTO>) {
            val serverResponse: MovieListDTO? = response.body()
            liveDataToObserve.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable("Ошибка загрузки"))
                }
            )
        }

        override fun onFailure(call: Call<MovieListDTO>, t: Throwable) {
            liveDataToObserve.postValue(AppState.Error(Throwable(t.message ?: "Ошибка загрузки")))
        }

        private fun checkResponse(serverResponse: MovieListDTO): AppState {
            return AppState.SuccessPlay(serverResponse)
        }
    }
    private val callBackCome = object : Callback<MovieListDTO> {

        @Throws(IOException::class)
        override fun onResponse(call: Call<MovieListDTO>, response: Response<MovieListDTO>) {
            val serverResponse: MovieListDTO? = response.body()
            liveDataToObserve.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable("Ошибка загрузки"))
                }
            )
        }

        override fun onFailure(call: Call<MovieListDTO>, t: Throwable) {
            liveDataToObserve.postValue(AppState.Error(Throwable(t.message ?: "Ошибка загрузки")))
        }

        private fun checkResponse(serverResponse: MovieListDTO): AppState {
            return AppState.SuccessCome(serverResponse)
        }
    }
    fun getDataFromLocalSource(lan:String) {
        liveDataToObserve.value = AppState.Loading
        mainRepositoryImpl.getNowPlayingFromLocalStorage(lan,callBackPlay,1)
        mainRepositoryImpl.getUpcomingFromLocalStorage(lan,callBackCome,1)
    }
}