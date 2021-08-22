package com.cocos.ammymovie.ui.common

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cocos.ammymovie.App.Companion.getMovieDao
import com.cocos.ammymovie.domain.repository.MainRepository
import com.cocos.ammymovie.domain.repository.impls.MainRepositoryImpl
import com.cocos.ammymovie.domain.repository.impls.web.RemoteDataSource

class LocalMovieViewModel(
    val localLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val mainRepositoryImpl: MainRepository = MainRepositoryImpl(
        RemoteDataSource(),
        getMovieDao(),
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