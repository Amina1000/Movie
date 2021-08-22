package com.cocos.ammymovie.domain.model

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