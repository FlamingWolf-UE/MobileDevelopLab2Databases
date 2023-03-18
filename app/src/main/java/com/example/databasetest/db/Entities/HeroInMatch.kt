package com.example.databasetest.db.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hero_in_match", primaryKeys = ["idhero", "match_id"])
data class HeroInMatch(

    @ColumnInfo(name="idhero")
    val hero_id : Int,

    @ColumnInfo(name="match_id")
    val match_id : Int,
)
