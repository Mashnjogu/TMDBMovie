package com.example.tmdbmovie.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tmdbmovie.data.local.entity.FavoriteTvEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TvDao {
    @Query("SELECT * FROM favoritetventity ORDER BY date_added DESC")
     fun getAllTvShows(): Flow<List<FavoriteTvEntity>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShow(tvEntity: FavoriteTvEntity)
    @Delete
    suspend fun deleteTvShow(tvEntity: FavoriteTvEntity)
    @Query("SELECT EXISTS (SELECT * FROM favoritetventity WHERE id = :id)")
    suspend fun tvShowExists(id: Int): Boolean
}