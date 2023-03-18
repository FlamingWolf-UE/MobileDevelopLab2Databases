package com.example.databasetest.db.Entities

import androidx.room.Embedded
import androidx.room.Relation

data class MatchWithMap(
    @Embedded
    val match : Match,
    @Relation(
        parentColumn = "map_id",
        entityColumn = "map_id"
    )
    val map : Map
)
