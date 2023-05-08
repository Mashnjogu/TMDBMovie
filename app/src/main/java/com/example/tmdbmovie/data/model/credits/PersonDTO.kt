package com.example.tmdbmovie.data.model.credits

import com.google.gson.annotations.SerializedName

data class PersonDTO(
    @SerializedName("character")
    val character: String?,
    @SerializedName("department")
    val department: String?,
    @SerializedName("id")
    val id: Int,
    @SerializedName("job")
    val job: String?,
    @SerializedName("known_for_department")
    val knownForDepartment: String?,
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_path")
    val profilePath: String?
)
