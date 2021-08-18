package com.example.ammymovie.ui.common

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ammymovie.App
import com.example.ammymovie.domain.repository.MainRepository
import com.example.ammymovie.domain.repository.impls.MainRepositoryImpl
import com.example.ammymovie.domain.repository.impls.web.RemoteDataSource

class LocalMovieViewModel(
    val localLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val mainRepositoryImpl: MainRepository = MainRepositoryImpl(
        RemoteDataSource(),
        App.getMovieDao(),
        Handler(Looper.getMainLooper())
    )
) : ViewModel() {


    fun getMovieFromLocal() {
        localLiveData.value = AppState.Loading
        mainRepositoryImpl.getMovieFromLocalStorage(
            { repos ->
                localLiveData.postValue(AppState.Success(repos))
            },
            false
        )

    }
}