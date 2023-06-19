package com.example.tmdbmovie.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbmovie.data.local.entity.FavoriteMovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM favoritemovieentity ORDER BY date_added DESC")
    suspend fun getAllMovies(): List<FavoriteMovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieEntity: FavoriteMovieEntity)

    @Delete
    suspend fun deleteMovie(movieEntity: FavoriteMovieEntity)

    @Query("SELECT EXISTS (SELECT * FROM favoritemovieentity WHERE id=:id)")
    suspend fun movieExists(id: Int): Boolean
}