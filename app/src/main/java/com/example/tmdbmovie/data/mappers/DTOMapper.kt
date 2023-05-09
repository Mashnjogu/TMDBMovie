package com.example.tmdbmovie.data.mappers

import com.example.tmdbmovie.data.model.credits.CreditsDTO
import com.example.tmdbmovie.data.model.credits.PersonDTO
import com.example.tmdbmovie.data.model.genre.Genre
import com.example.tmdbmovie.data.model.genre.MovieGenreDTO
import com.example.tmdbmovie.data.model.movies.MovieDataDTO
import com.example.tmdbmovie.data.model.movies.MovieDetailDTO
import com.example.tmdbmovie.data.model.movies.MoviesDTO
import com.example.tmdbmovie.data.model.videos.VideoDTO
import com.example.tmdbmovie.data.model.videos.VideoListDTO
import com.example.tmdbmovie.domain.model.*

fun MovieGenreDTO.toGenre() = Genre(id, name)

fun PersonDTO.toPerson() = Person(character,department,id,job,knownForDepartment,name,profilePath)

fun CreditsDTO.toCredit() = Credits(cast.map { it.toPerson() }, crew.map { it.toPerson() })

fun VideoDTO.toVideo() = Video(key, name, publishedAt, site, type)

fun VideoListDTO.toVideoList() = VideoList(results.map { it.toVideo() })

fun MovieDataDTO.toMovie() = Movie(id,title,backdrop_path,genre_ids,original_language,overview,poster_path,release_date,vote_average)

fun MoviesDTO.toMovieList() = MovieList(results.map { it.toMovie() }, totalResults)

fun MovieDetailDTO.toMovieDetail() = MovieInfo(
    id = id,
    title= title,
    backdrop_path = backdrop_path,
    genres= genres.map { it.toGenre() },
    original_language = original_language,
    overview = overview,
    poster_path = poster_path,
    release_date = release_date,
    vote_average = vote_average,
    vote_count = vote_count,
    credits = credits.toCredit(),
    videos = videos.toVideoList(),
    similar = similar.toMovieList() ,
    runtime = runtime
)