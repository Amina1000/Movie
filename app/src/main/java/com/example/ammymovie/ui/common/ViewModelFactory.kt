package com.example.ammymovie.ui.common

import android.app.Application
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ammymovie.App
import com.example.ammymovie.domain.repository.impls.MainRepositoryImpl
import com.example.ammymovie.domain.repository.impls.web.RemoteDataSource
import com.example.ammymovie.ui.main.MainViewModel
import com.example.ammymovie.ui.search.SearchViewModel

class MainViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
       MainViewModel(application, MainRepositoryImpl(
           RemoteDataSource(),
           App.getMovieDao(),
           Handler(Looper.getMainLooper())
       ))as T
}
class SearchViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        SearchViewModel(application, MainRepositoryImpl(
            RemoteDataSource(),
            App.getMovieDao(),
            Handler(Looper.getMainLooper())
        ) ) as T
}