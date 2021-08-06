package com.example.ammymovie.domain.model

import android.os.Parcel
import android.os.Parcelable

/**
 * homework com.example.ammymovie.domain.model
 *
 * @author Amina
 * 29.07.2021
 */
class MovieListDTO(var results: ArrayList<MovieDTO>?): Parcelable {
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(MovieDTO))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(results)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieListDTO> {
        override fun createFromParcel(parcel: Parcel): MovieListDTO {
            return MovieListDTO(parcel)
        }

        override fun newArray(size: Int): Array<MovieListDTO?> {
            return arrayOfNulls(size)
        }
    }
    fun isNotEmpty():Boolean{
        return this.results?.isNotEmpty()?:false
    }
}