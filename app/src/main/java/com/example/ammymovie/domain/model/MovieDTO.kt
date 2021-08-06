package com.example.ammymovie.domain.model

import android.os.Parcel
import android.os.Parcelable

/**
 * homework com.example.ammymovie.domain.model
 *
 * @author Amina
 * 21.07.2021
 */

data class MovieDTO(
    val id:Int?,
    val title: String?,
    val original_title: String?,
    val release_date: String?,
    val popularity: Double?,
    val poster_path:String?,
    val overview: String?,
    val duration: String?,
    val budget: Int?,
    val revenue: Int?,
    var favorite:Boolean=false,
    var section:Int=0

):Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Double::class.java.classLoader) as? Double,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(title)
        parcel.writeString(original_title)
        parcel.writeString(release_date)
        parcel.writeValue(popularity)
        parcel.writeString(poster_path)
        parcel.writeString(overview)
        parcel.writeString(duration)
        parcel.writeValue(budget)
        parcel.writeValue(revenue)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MovieDTO> {
        override fun createFromParcel(parcel: Parcel): MovieDTO {
            return MovieDTO(parcel)
        }

        override fun newArray(size: Int): Array<MovieDTO?> {
            return arrayOfNulls(size)
        }
    }
}