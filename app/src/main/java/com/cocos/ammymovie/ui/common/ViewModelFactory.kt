package com.cocos.ammymovie.ui.common

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cocos.ammymovie.App.Companion.getMovieDao
import com.cocos.ammymovie.domain.repository.impls.MainRepositoryImpl
import com.cocos.ammymovie.domain.repository.impls.web.RemoteDataSource
import com.cocos.ammymovie.ui.main.MainViewModel
import com.cocos.ammymovie.ui.search.SearchViewModel

class MainViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
       MainViewModel(application, MainRepositoryImpl(
           RemoteDataSource(),
           getMovieDao(),
           Handler(Looper.getMainLooper())
       ))as T
}
class SearchViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        SearchViewModel(application, MainRepositoryImpl(
            RemoteDataSource(),
            com.cocos.ammymovie.App.getMovieDao(),
            Handler(Looper.getMainLooper())
        ) ) as T
}