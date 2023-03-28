package com.example.tmdbmovie.domain.helper

import com.example.tmdbmovie.data.remote.MoviesDTO
import com.example.tmdbmovie.domain.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieApiHelper {

    suspend fun getPopularMovies(): Flow<MoviesDTO>

    suspend fun getTopRatedMovies(): Flow<MoviesDTO>

    suspend fun getLatestMovies(): Flow<MoviesDTO>
}