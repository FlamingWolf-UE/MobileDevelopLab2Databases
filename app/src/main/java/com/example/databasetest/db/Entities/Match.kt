package com.example.databasetest.db.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "match")
data class Match(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="match_id")
    val id: Int,
    @ColumnInfo(name="match_playtime")
    val playtime: Int,
    @ColumnInfo(name="map_id")
    val mapId: Int
)
