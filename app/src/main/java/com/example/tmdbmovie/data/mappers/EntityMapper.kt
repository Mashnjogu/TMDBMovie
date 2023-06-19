package com.example.tmdbmovie.data.mappers

import com.example.tmdbmovie.data.local.FavoriteTv
import com.example.tmdbmovie.data.local.entity.FavoriteTvEntity

fun FavoriteTv.toFavoriteTvEntity() = FavoriteTvEntity(id, numOfSeasons, firstAirDate, name, backdropPath, voteAverage, voteCount, date)

fun FavoriteTvEntity.toFavoriteTv() = FavoriteTv(id, numOfSeasons, firstAirDate, name, backdropPath, voteAverage, voteCount, date)

//fun FavoriteMovie.toFavoriteMovieEntity() = FavoriteMovieEntity(id, posterPath, releaseDate, runtime, title, voteAverage, voteCount, date)
//
//fun FavoriteMovieEntity.toFavoriteMovie() = FavoriteMovie(id, posterPath, releaseDate, runtime, title, voteAverage, voteCount, date)