package com.example.tmdbmovie.di

import com.example.tmdbmovie.BuildConfig
import com.example.tmdbmovie.R
import com.example.tmdbmovie.data.helper.MovieApiHelperImpl
import com.example.tmdbmovie.data.remote.MovieApiService
import com.example.tmdbmovie.domain.helper.MovieApiHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object MoviesModule{

    @Provides
    fun provideOkhhtpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor {
            val original = it.request()
            val originalHttpUrl: HttpUrl = original.url
            val url = originalHttpUrl.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY)
                .build()

            val requestBuilder = original.newBuilder().url(url)
            val request = requestBuilder.build()
            it.proceed(request)
        }.build()

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("${R.string.tmdb_base_url}")
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    @Provides
    fun provideMoviesService(retrofit: Retrofit): MovieApiService{
        return retrofit.create(MovieApiService::class.java)
    }

    @Provides
    fun provideMoviesApiHelper(apiService: MovieApiService): MovieApiHelper{
        return MovieApiHelperImpl(apiService)
    }

}