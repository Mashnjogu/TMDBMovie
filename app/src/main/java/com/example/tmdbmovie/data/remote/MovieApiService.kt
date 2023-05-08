package com.example.tmdbmovie.data.remote

import com.example.tmdbmovie.data.model.genre.MovieGenre
import com.example.tmdbmovie.data.model.movies.MovieDetailDTO
import com.example.tmdbmovie.data.model.movies.MoviesDTO
import com.example.tmdbmovie.data.model.tvshows.TvShowDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(): MoviesDTO

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(): MoviesDTO

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(): MoviesDTO

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(): MoviesDTO

    //TV Shows
    @GET("tv/popular")
    suspend fun getPopularTVShows(): TvShowDTO

    @GET("tv/top_rated")
    suspend fun getTopRatedTVShows(): TvShowDTO

    @GET("tv/on_the_air")
    suspend fun getOnAirShows(): TvShowDTO

    @GET( "trending/movie/week")
    suspend fun getTrendingMovies(): MoviesDTO

    @GET( "trending/tv/week")
    suspend fun getTrendingShows(): TvShowDTO

    @GET("genre/movie/list")
    suspend fun getMovieGenres(): MovieGenre

    @GET("movie/{movie_id}?&append_to_response=credits,videos,similar")
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): MovieDetailDTO

}