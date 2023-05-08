package com.example.tmdbmovie.domain.model

data class Credits(
    val cast: List<Person>,
    val crew: List<Person>
){
    fun getDirector() = crew.find {
        it.job == "Director"
    }?.name ?: ""

    fun getWriters() = crew.filter {
        it.job == "Writing"
    }.joinToString { it.name + " (${it.job})" }

    companion object{
        val empty = Credits(
            cast = emptyList(),
            crew = emptyList(),
        )
    }
}
