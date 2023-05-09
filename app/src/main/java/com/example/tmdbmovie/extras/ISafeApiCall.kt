package com.example.tmdbmovie.extras

interface ISafeApiCall {
    suspend  fun <T> execute( body: suspend () -> T): Resource<T>
}