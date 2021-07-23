package com.example.ammymovie.ui.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ammymovie.domain.model.MovieDTO
import com.example.ammymovie.domain.repository.DetailsRepository
import com.example.ammymovie.domain.repository.DetailsRepositoryImpl
import com.example.ammymovie.domain.repository.MovieLoaderListener
import com.example.ammymovie.domain.repository.RemoteDataSource
import com.example.ammymovie.ui.common.AppState

/**
 * homework com.example.ammymovie.ui.ditail.viewmodel
 *
 * @author Amina
 * 23.07.2021
 */
@RequiresApi(Build.VERSION_CODES.N)
class DetailsViewModel (
    private val detailsLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val detailsRepositoryImpl: DetailsRepository = DetailsRepositoryImpl(RemoteDataSource())
) : ViewModel() {
    fun getLiveData() = detailsLiveData

    fun getMovieFromRemoteSource(movieLink:Any) {
        detailsLiveData.value = AppState.Loading
        detailsRepositoryImpl.getMovieDetailsFromServer(movieLink,onLoadListener)
    }

    private val onLoadListener=
        object : MovieLoaderListener {

            override fun onLoaded(movieDTO: MovieDTO) {
                detailsLiveData.postValue(AppState.SuccessDetails(movieDTO))
            }

            override fun onFailed(throwable: Throwable) {
                detailsLiveData.postValue(AppState.Error(Throwable("Ошибка загрузки")))
            }
        }

}