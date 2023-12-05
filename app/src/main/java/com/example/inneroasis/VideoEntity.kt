package com.example.inneroasis

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "video_table")
data class VideoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "videoId") val videoId: String?,
    @ColumnInfo(name = "videoTitle") val title: String?,
    @ColumnInfo(name = "videoDesc") val description: String?,
) : Serializable