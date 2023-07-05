package com.example.tmdbmovie.domain.repository

import com.example.tmdbmovie.data.FavoriteMovie
import com.example.tmdbmovie.data.local.FavoriteTv
import com.example.tmdbmovie.data.local.entity.FavoriteTvEntity
import com.example.tmdbmovie.data.model.genre.MovieGenre
import com.example.tmdbmovie.data.model.movies.MovieDetailDTO
import com.example.tmdbmovie.data.model.movies.MoviesDTO
import com.example.tmdbmovie.data.model.search.MultiSearchResponse
import com.example.tmdbmovie.data.model.tvshows.TvDetailDataDTO
import com.example.tmdbmovie.data.model.tvshows.TvShowDTO
import com.example.tmdbmovie.domain.model.MovieInfo
import com.example.tmdbmovie.extras.Resource
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    suspend fun getPopularMovies(): Flow<MoviesDTO>

    suspend fun getTopRatedMovies(): Flow<MoviesDTO>

    suspend fun getNowPlayingMovies(): Flow<MoviesDTO>

    suspend fun getUpcomingMovies(): Flow<MoviesDTO>

    suspend fun getPopularTVShows(): Flow<TvShowDTO>

    suspend fun getTopRatedTVShows(): Flow<TvShowDTO>

    suspend fun getOnAirShows(): Flow<TvShowDTO>

    suspend fun getTrendingMovies(): Flow<MoviesDTO>

    suspend fun getTrendingShows(): Flow<TvShowDTO>

    suspend fun getMovieGenres(): Flow<MovieGenre>

    suspend fun getMovieDetails(movieId: Int): Flow<MovieDetailDTO>

    suspend fun getMultiSearch(query: String, page: Int): Flow<MultiSearchResponse>

    suspend fun getTvShowDetails(seriesId: Int): Flow<TvDetailDataDTO>

    suspend fun getFavTvShows(): Flow<List<FavoriteTv>>

    suspend fun addFavTvShow(tvShow: FavoriteTv)

    suspend fun deleteFavTvShow(tvShow: FavoriteTv)

    suspend fun getFavMovies(): Flow<List<FavoriteMovie>>

    suspend fun addFavMovie(movie: FavoriteMovie)

    suspend fun deleteFavMovie(movie: FavoriteMovie)


}