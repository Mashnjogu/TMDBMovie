package com.example.tmdbmovie.data.model.tvshows

import com.google.gson.annotations.SerializedName

data class Created_Data(
    @SerializedName("id")
    val id: Int,
    @SerializedName("credit_id")
    val creditId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("gender")
    val gender: Int,
    @SerializedName("profile_path")
    val profilePath: String
)
