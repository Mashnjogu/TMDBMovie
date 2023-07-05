package com.example.tmdbmovie.data.local.database

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.tmdbmovie.data.local.dao.MovieDao
import com.example.tmdbmovie.data.local.dao.TvDao
import com.example.tmdbmovie.data.local.entity.FavoriteMovieEntity
import com.example.tmdbmovie.data.local.entity.FavoriteTvEntity

@Database(entities = [FavoriteMovieEntity::class, FavoriteTvEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun tvDao(): TvDao



    companion object{

        @Volatile
        private var INSTANCE: AppDatabase? = null

    }

}
