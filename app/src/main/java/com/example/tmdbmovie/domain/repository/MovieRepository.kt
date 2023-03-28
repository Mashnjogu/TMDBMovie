package com.example.tmdbmovie.domain.repository

import com.example.tmdbmovie.data.remote.MoviesDTO
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovies(): Flow<MoviesDTO>

    suspend fun getTopRatedMovies(): Flow<MoviesDTO>

    suspend fun getLatestMovies(): Flow<MoviesDTO>
}