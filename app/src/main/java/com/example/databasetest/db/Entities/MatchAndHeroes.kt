package com.example.databasetest.db.Entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class MatchAndHeroes(
    @Embedded
    val match : Match,
    @Relation(

        parentColumn = "match_id",
        entityColumn = "idhero",
        associateBy = Junction(HeroInMatch::class)
    )
    val hero : List<Hero>
)
