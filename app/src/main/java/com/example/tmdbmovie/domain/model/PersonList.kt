package com.example.tmdbmovie.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class PersonList(
    val result: List<Person>,
    val totalResults: Int
){
    companion object{
        val empty = PersonList(
            result = emptyList(),
            totalResults = 0
        )
    }
}

@Parcelize
data class Person(
    val character: String?,
    val department: String?,
    val id: Int,
    val job: String?,
    val knownForDepartment: String?,
    val name: String,
    val profilePath: String?
): Parcelable
