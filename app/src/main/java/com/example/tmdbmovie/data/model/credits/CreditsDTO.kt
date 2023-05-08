package com.example.tmdbmovie.data.model.credits

import com.google.gson.annotations.SerializedName

data class CreditsDTO(
    @SerializedName("cast")
    val cast: List<PersonDTO>,
    @SerializedName("crew")
    val crew: List<PersonDTO>
)
