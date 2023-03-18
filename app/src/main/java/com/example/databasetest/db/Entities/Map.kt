package com.example.databasetest.db.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "map")
data class Map(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="map_id")
    val id : Int,
    @ColumnInfo(name="map_rule")
    val rule : String,
    @ColumnInfo(name = "map_name")
    val name : String
)