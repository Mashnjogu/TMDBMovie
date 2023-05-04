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
            emit(apiService.getTopRatedMovies())
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override suspend fun getNowPlayingMovies() = flow {
        try {
            emit(apiService.getNowPlayingMovies())
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override suspend fun getUpcomingMovies() = flow{
        try{
            emit(apiService.getUpcomingMovies())
        }catch(e: Exception){
            e.printStackTrace()
        }
    }

    override suspend fun getTrendingMovies() = flow {
        try{
            emit(apiService.getTrendingMovies())
        }catch(e: Exception){
            e.printStackTrace()
        }
    }

    override suspend fun getMovieGenres() = flow{
        try{
            emit(apiService.getMovieGenres())
        }catch(e: Exception){
            e.printStackTrace()
        }
    }

    override suspend fun getPopularTVShows() = flow {
        try{
            emit(apiService.getPopularTVShows())
        }catch(e: Exception){
            e.printStackTrace()
        }
    }

    override suspend fun getTopRatedTVShows() = flow {
        try{
            emit(apiService.getTopRatedTVShows())
        }catch(e: Exception){
            e.printStackTrace()
        }
    }

    override suspend fun getOnAirShows() = flow {
        try{
            emit(apiService.getOnAirShows())
        }catch(e: Exception){
            e.printStackTrace()
        }
    }

    override suspend fun getTrendingShows() = flow{
        try{
            emit(apiService.getTrendingShows())
        }catch(e: Exception){
            e.printStackTrace()
        }
    }


}