package com.example.tmdbmovie.data.repository

import com.example.tmdbmovie.data.remote.MoviesDTO
import com.example.tmdbmovie.domain.helper.MovieApiHelper
import com.example.tmdbmovie.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val moviesApiHelper: MovieApiHelper
): MovieRepository{
    override suspend fun getPopularMovies(): Flow<MoviesDTO> = moviesApiHelper.getPopularMovies()

    override suspend fun getTopRatedMovies(): Flow<MoviesDTO> = moviesApiHelper.getTopRatedMovies()

    override suspend fun getLatestMovies(): Flow<MoviesDTO> = moviesApiHelper.getLatestMovies()

}