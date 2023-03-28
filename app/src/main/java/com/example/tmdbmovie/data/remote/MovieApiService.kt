package com.example.tmdbmovie.data.remote

import retrofit2.http.GET

interface MovieApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(): MoviesDTO

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): MoviesDTO

    @GET("movie/latest")
    suspend fun getLatestMovies(): MoviesDTO
}