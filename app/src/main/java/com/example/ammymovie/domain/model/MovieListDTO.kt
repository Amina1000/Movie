package com.example.ammymovie.domain.model

import android.os.Parcel
import android.os.Parcelable

/**
 * homework com.example.ammymovie.domain.model
 *
 * @author Amina
 * 29.07.2021
 */
class MovieListDTO(var results: ArrayList<MovieDTO>?) {
    fun isNotEmpty():Boolean{
        return this.results?.isNotEmpty()?:false
    }
}