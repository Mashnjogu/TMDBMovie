package com.example.tmdbmovie.data.model.tvshows

import com.example.tmdbmovie.data.model.credits.CreditsDTO
import com.example.tmdbmovie.data.model.genre.MovieGenreDTO
import com.example.tmdbmovie.data.model.videos.VideoListDTO
import com.google.gson.annotations.SerializedName

data class TvDetailDataDTO(
    @SerializedName("created_by")
    val created_by: List<Created_Data>,
    @SerializedName("first_air_date")
    val firstAirDate: String,
    @SerializedName("genres")
    val genres: List<MovieGenreDTO>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("in_production")
    val in_production: Boolean,
    @SerializedName("last_episode_to_air")
    val last_episode_to_air: LastEpisodeToAir,
    @SerializedName("name")
    val name: String,
//    @SerializedName("next_episode_to_air")
//    val next_episode_to_air: Boolean?,
    @SerializedName("number_of_episodes")
    val num_of_episodes: Int,
    @SerializedName("number_of_seasons")
    val num_of_seasons: Int,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("backdrop_path")
    val backdrop_path: String?,
    @SerializedName("poster_path")
    val posterPath: String?,
    @SerializedName("tagline")
    val tagline: String,
    @SerializedName("vote_average")
    val vote_average: Double,
    @SerializedName("vote_count")
    val vote_count: Int,
    @SerializedName("credits")
    val credits: CreditsDTO,
    @SerializedName("videos")
    val videos: VideoListDTO,
    @SerializedName("similar")
    val similar: TvShowDTO
)