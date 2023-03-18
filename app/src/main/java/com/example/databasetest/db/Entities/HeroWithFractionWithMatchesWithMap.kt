package com.example.databasetest.db.Entities

import androidx.room.Embedded
import androidx.room.Relation

data class HeroWithFractionWithMatchesWithMap(
    @Embedded
    val hero : HeroWithFraction,
    @Relation(
        entity = Match::class,
        parentColumn = "idhero",
        entityColumn = "match_id"
    )
    val matches : List<MatchWithMap>
)