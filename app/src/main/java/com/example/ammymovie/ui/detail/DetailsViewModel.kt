package com.example.ammymovie.ui.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ammymovie.domain.model.MovieDTO
import com.example.ammymovie.domain.repository.DetailsRepository
import com.example.ammymovie.domain.repository.impls.DetailsRepositoryImpl
import com.example.ammymovie.domain.repository.impls.web.RemoteDataSource
import com.example.ammymovie.ui.common.AppState
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import java.io.IOException

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
        detailsRepositoryImpl.getMovieDetailsFromServer(movieLink,callBack)
    }

    private val callBack = object : Callback {

        @Throws(IOException::class)
        override fun onResponse(call: Call?, response: Response) {
            val serverResponse: String? = response.body()?.string()
            detailsLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable("Ошибка загрузки"))
                }
            )
        }

        override fun onFailure(call: Call?, e: IOException?) {
            detailsLiveData.postValue(AppState.Error(Throwable("Ошибка загрузки")))
        }

        private fun checkResponse(serverResponse: String): AppState {
            val movieDTO= Gson().fromJson(serverResponse,MovieDTO::class.java)
            return if (movieDTO == null) {
                AppState.Error(Throwable("Данных по фильму нет"))
            } else {
                AppState.SuccessDetails(movieDTO)
            }
        }
    }

}