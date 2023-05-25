package com.example.tmdbmovie.data.repository

import com.example.tmdbmovie.data.model.genre.MovieGenre
import com.example.tmdbmovie.data.model.movies.MovieDetailDTO
import com.example.tmdbmovie.data.model.movies.MoviesDTO
import com.example.tmdbmovie.data.model.tvshows.TvShowDTO
import com.example.tmdbmovie.domain.helper.MovieApiHelper
import com.example.tmdbmovie.domain.model.MovieInfo
import com.example.tmdbmovie.domain.repository.MovieRepository
import com.example.tmdbmovie.extras.Resource
import com.example.tmdbmovie.extras.SafeApiCall
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val moviesApiHelper: MovieApiHelper,
    private val safeApiCall: SafeApiCall
): MovieRepository{
    override suspend fun getPopularMovies(): Flow<MoviesDTO> {
        return moviesApiHelper.getPopularMovies()
    }

    override suspend fun getTopRatedMovies(): Flow<MoviesDTO>{
        return moviesApiHelper.getTopRatedMovies()
    }

    override suspend fun getNowPlayingMovies(): Flow<MoviesDTO> = moviesApiHelper.getNowPlayingMovies()

    override suspend fun getUpcomingMovies(): Flow<MoviesDTO> {
        return moviesApiHelper.getUpcomingMovies()
    }

    override suspend fun getPopularTVShows(): Flow<TvShowDTO> {
        return moviesApiHelper.getPopularTVShows()
    }

    override suspend fun getTopRatedTVShows(): Flow<TvShowDTO> {
        return moviesApiHelper.getTopRatedTVShows()
    }

    override suspend fun getOnAirShows(): Flow<TvShowDTO> {
        return moviesApiHelper.getOnAirShows()
    }

    override suspend fun getTrendingMovies(): Flow<MoviesDTO> {
        return moviesApiHelper.getTrendingMovies()
    }

    override suspend fun getTrendingShows(): Flow<TvShowDTO> {
        return moviesApiHelper.getTrendingShows()
    }

    override suspend fun getMovieGenres(): Flow<MovieGenre> {
        return moviesApiHelper.getMovieGenres()
    }

    override suspend fun getMovieDetails(movieId: Int): Flow<MovieDetailDTO> {
        return moviesApiHelper.getMovieDetails(movieId)
    }


}