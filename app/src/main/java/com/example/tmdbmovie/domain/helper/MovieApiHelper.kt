package com.example.tmdbmovie.domain.helper

import com.example.tmdbmovie.data.model.genre.MovieGenre
import com.example.tmdbmovie.data.model.movies.MovieDetailDTO
import com.example.tmdbmovie.data.model.movies.MoviesDTO
import com.example.tmdbmovie.data.model.search.MultiSearchResponse
import com.example.tmdbmovie.data.model.tvshows.TvDetailDataDTO
import com.example.tmdbmovie.data.model.tvshows.TvShowDTO
import com.example.tmdbmovie.domain.model.MovieInfo
import com.example.tmdbmovie.extras.Resource
import kotlinx.coroutines.flow.Flow

interface MovieApiHelper {

    //Movies
    suspend fun getPopularMovies(): Flow<MoviesDTO>

    suspend fun getTopRatedMovies(): Flow<MoviesDTO>

    suspend fun getNowPlayingMovies(): Flow<MoviesDTO>

    suspend fun getUpcomingMovies(): Flow<MoviesDTO>

    suspend fun getTrendingMovies(): Flow<MoviesDTO>

    suspend fun getMovieGenres(): Flow<MovieGenre>

    suspend fun getMovieDetails(movieId: Int): Flow<MovieDetailDTO>


    //TV Shows
    suspend fun getPopularTVShows(): Flow<TvShowDTO>

    suspend fun getTopRatedTVShows(): Flow<TvShowDTO>

    suspend fun getOnAirShows(): Flow<TvShowDTO>

    suspend fun getTrendingShows(): Flow<TvShowDTO>

    suspend fun getTvShowDetails(seriesId: Int): Flow<TvDetailDataDTO>

    //MultiSearch
    suspend fun getMultiSearch(query: String, page: Int): Flow<MultiSearchResponse>
}