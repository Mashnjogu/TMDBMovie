package com.example.tmdbmovie.di

import com.example.tmdbmovie.data.repository.MovieRepositoryImpl
import com.example.tmdbmovie.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMoviesRepository(
        movieRepositoryImpl: MovieRepositoryImpl
    ): MovieRepository
}