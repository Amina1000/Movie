package com.example.ammymovie.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ammymovie.domain.repository.MainRepository
import com.example.ammymovie.domain.repository.MainRepositoryImpl
import com.example.ammymovie.ui.common.AppState
import java.lang.Thread.sleep

class MainViewModel(
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData(),
    private val mainRepositoryImpl: MainRepository = MainRepositoryImpl()
) : ViewModel() {

    // Получаем данные
    fun getLiveData() = liveDataToObserve

    fun getDataFromLocalSource() {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(1000)
            liveDataToObserve.postValue(
                AppState.Success(
                    mainRepositoryImpl.getNowPlayingFromLocalStorage(),
                    mainRepositoryImpl.getUpcomingFromLocalStorage()
                )
            )
        }.start()
    }
}