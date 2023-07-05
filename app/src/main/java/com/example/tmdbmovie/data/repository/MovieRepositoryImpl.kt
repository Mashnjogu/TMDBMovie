package com.example.tmdbmovie.data.repository

import com.example.tmdbmovie.data.FavoriteMovie
import com.example.tmdbmovie.data.local.FavoriteTv
import com.example.tmdbmovie.data.local.dao.MovieDao
import com.example.tmdbmovie.data.local.dao.TvDao

import com.example.tmdbmovie.data.model.genre.MovieGenre
import com.example.tmdbmovie.data.model.movies.MovieDetailDTO
import com.example.tmdbmovie.data.model.movies.MoviesDTO
import com.example.tmdbmovie.data.model.search.MultiSearchResponse
import com.example.tmdbmovie.data.model.tvshows.TvDetailDataDTO
import com.example.tmdbmovie.data.model.tvshows.TvShowDTO
import com.example.tmdbmovie.domain.helper.MovieApiHelper
import com.example.tmdbmovie.data.mappers.*
import com.example.tmdbmovie.domain.repository.MovieRepository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val moviesApiHelper: MovieApiHelper,
    private val tvDao: TvDao,
    private val movieDao: MovieDao
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

    override suspend fun getMultiSearch(query: String, page: Int): Flow<MultiSearchResponse> {
        return moviesApiHelper.getMultiSearch(query, page)
    }

    override suspend fun getTvShowDetails(seriesId: Int): Flow<TvDetailDataDTO> {
        return moviesApiHelper.getTvShowDetails(seriesId)
    }

    override suspend fun getFavTvShows(): Flow<List<FavoriteTv>> {
        return tvDao.getAllTvShows().map {
            it.map {
                it.toFavoriteTv()
            }
        }

    }

    override suspend fun addFavTvShow(tvShow: FavoriteTv) {
        return tvDao.insertTvShow(tvShow.toFavoriteTvEntity())
    }

    override suspend fun deleteFavTvShow(tvShow: FavoriteTv) {
        return tvDao.deleteTvShow(tvShow.toFavoriteTvEntity())
    }

    override suspend fun getFavMovies(): Flow<List<FavoriteMovie>> {
        return movieDao.getAllMovies().map {
            it.map {
                it.toFavoriteMovie()
            }
        }
    }

    override suspend fun addFavMovie(movie: FavoriteMovie) {
        return movieDao.insertMovie(movie.toFavoriteMovieEntity())
    }

    override suspend fun deleteFavMovie(movie: FavoriteMovie) {
        return movieDao.deleteMovie(movie.toFavoriteMovieEntity())
    }

}