package com.example.ammymovie.ui.detail

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.ammymovie.domain.model.MovieDTO
import com.example.ammymovie.domain.repository.DetailsRepository
import com.example.ammymovie.domain.repository.impls.web.WebDetailsRepositoryImpl
import com.example.ammymovie.domain.repository.impls.web.RemoteDataSource
import com.example.ammymovie.ui.common.AppState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.io.IOException

/**
 * homework com.example.ammymovie.ui.ditail.viewmodel
 *
 * @author Amina
 * 23.07.2021
 */
@RequiresApi(Build.VERSION_CODES.N)
class DetailsViewModel (
    val detailsLiveData: MutableLiveData<AppState> = MutableLiveData(),
    private val detailsRepositoryImpl: DetailsRepository = WebDetailsRepositoryImpl(RemoteDataSource())
) : ViewModel() {

    fun getMovieFromRemoteSource(movieLink:Int?, lan:String) {
        detailsLiveData.value = AppState.Loading
        detailsRepositoryImpl.getMovieDetailsFromServer(movieLink,lan,callBack)
    }

    private val callBack = object : Callback<MovieDTO> {

        @Throws(IOException::class)
        override fun onResponse(call: Call<MovieDTO>, response: Response<MovieDTO>) {
            val serverResponse: MovieDTO? = response.body()
            detailsLiveData.postValue(
                if (response.isSuccessful && serverResponse != null) {
                    checkResponse(serverResponse)
                } else {
                    AppState.Error(Throwable("Ошибка загрузки"))
                }
            )
        }

        override fun onFailure(call: Call<MovieDTO>, t: Throwable) {
            detailsLiveData.postValue(AppState.Error(Throwable(t.message ?: "Ошибка загрузки")))
        }

        private fun checkResponse(serverResponse: MovieDTO): AppState {
               return AppState.SuccessDetails(serverResponse)
        }
    }

}