package com.cocos.ammymovie.ui.search

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.preference.PreferenceManager
import com.cocos.ammymovie.domain.model.MovieListDTO
import com.cocos.ammymovie.domain.repository.MainRepository
import com.cocos.ammymovie.ui.common.AppState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class SearchViewModel(
    app: Application,
    private val mainRepositoryImpl: MainRepository
) : ViewModel(){
    val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()
    private val preferenceManager = PreferenceManager.getDefaultSharedPreferences(app)
    private var adultAdded = !preferenceManager.getBoolean("adultClick", true)
    private fun callBack(): Callback<MovieListDTO> {
        return object : Callback<MovieListDTO> {

            @Throws(IOException::class)
            override fun onResponse(call: Call<MovieListDTO>, response: Response<MovieListDTO>) {
                val serverResponse: MovieListDTO? = response.body()
                if (response.isSuccessful && serverResponse != null) {
                    liveDataToObserve.postValue(AppState.Success(serverResponse))
                } else {
                    AppState.Error(Throwable("Load error"))
                }
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

    fun getSearchingMovieServer(lan: String, query:String) {
        liveDataToObserve.value = AppState.Loading
        with(mainRepositoryImpl) {
            getSearchingMovieServer(
                query,
                lan,
                callBack(),
                1,
                adultAdded
            )
        }
    }
}