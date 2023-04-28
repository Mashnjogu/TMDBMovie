package com.example.tmdbmovie.extras

import com.example.tmdbmovie.data.model.movies.MovieDataDTO
import com.example.tmdbmovie.data.model.tvshows.TvShowDataDTO

sealed class TrendingMediaData{

    data class MovieData(val movie: MovieDataDTO): TrendingMediaData()
    data class TvShowData(val tvShow: TvShowDataDTO): TrendingMediaData()

}
