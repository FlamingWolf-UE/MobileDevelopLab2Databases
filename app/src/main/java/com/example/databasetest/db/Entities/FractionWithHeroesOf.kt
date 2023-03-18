package com.example.databasetest.db.Entities

import androidx.room.Embedded
import androidx.room.Relation

data class FractionWithHeroesOf(
    @Embedded
    val fraction: Fraction,
    @Relation(
        parentColumn = "fraction_id",
        entityColumn = "fraction_id"
    )
    val heroesConsisted: List<Hero>

)