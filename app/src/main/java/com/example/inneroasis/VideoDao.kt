package com.example.inneroasis

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface VideoDao {
    @Query("SELECT * FROM video_table")
    fun getAll(): Flow<List<VideoEntity>>

    @Insert
    fun insertAll(videos: List<VideoEntity>)

    @Insert
    fun insert(video: VideoEntity)

    @Delete
    fun delete(video: VideoEntity)

    @Query("DELETE FROM video_table")
    fun deleteAll()
}