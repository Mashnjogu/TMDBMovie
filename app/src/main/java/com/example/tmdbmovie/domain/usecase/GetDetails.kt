package com.example.tmdbmovie.domain.usecase

import com.example.tmdbmovie.domain.repository.MovieRepository
import com.example.tmdbmovie.extras.MediaType
import com.example.tmdbmovie.extras.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import javax.inject.Inject

class GetDetails @Inject constructor(private val repository: MovieRepository){

    operator fun invoke(mediaType: MediaType, id: Int, seasonNumber: Int? = null, episodeNumber: Int? = null)
    : Flow<Resource<Any>>  = flow{
        emit(
            when(mediaType){
                 MediaType.MOVIE ->{
                     repository.getMovieDetails(id)
                 }
                MediaType.TV -> {
                    repository.getMovieDetails(id)
                }
                MediaType.PERSON -> {
                    repository.getMovieDetails(id)
                }
            }
                    )
    }
}