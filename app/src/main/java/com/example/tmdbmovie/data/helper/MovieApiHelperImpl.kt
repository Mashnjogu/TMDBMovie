package com.example.tmdbmovie.data.helper

import com.example.tmdbmovie.data.remote.MovieApiService
import com.example.tmdbmovie.domain.helper.MovieApiHelper
import kotlinx.coroutines.flow.flow

class MovieApiHelperImpl(private val apiService: MovieApiService): MovieApiHelper{
    override suspend fun getPopularMovies() = flow {
        try {
            emit(apiService.getPopularMovies())
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override suspend fun getTopRatedMovies() = flow {
        try {
            emit(apiService.getPopularMovies())
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override suspend fun getLatestMovies() = flow {
        try {
            emit(apiService.getPopularMovies())
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

}