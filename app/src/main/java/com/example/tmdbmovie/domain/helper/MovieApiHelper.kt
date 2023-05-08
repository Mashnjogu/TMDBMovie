package com.example.tmdbmovie.domain.helper

import com.example.tmdbmovie.data.model.genre.MovieGenre
import com.example.tmdbmovie.data.model.movies.MoviesDTO
import com.example.tmdbmovie.data.model.tvshows.TvShowDTO
import kotlinx.coroutines.flow.Flow

interface MovieApiHelper {

    //Movies
    suspend fun getPopularMovies(): Flow<MoviesDTO>

    suspend fun getTopRatedMovies(): Flow<MoviesDTO>

    suspend fun getNowPlayingMovies(): Flow<MoviesDTO>

    suspend fun getUpcomingMovies(): Flow<MoviesDTO>

    suspend fun getTrendingMovies(): Flow<MoviesDTO>

    suspend fun getMovieGenres(): Flow<MovieGenre>

    suspend fun getMovieDetails(): Resource<>


    //TV Shows
    suspend fun getPopularTVShows(): Flow<TvShowDTO>

    suspend fun getTopRatedTVShows(): Flow<TvShowDTO>

    suspend fun getOnAirShows(): Flow<TvShowDTO>

    suspend fun getTrendingShows(): Flow<TvShowDTO>
}