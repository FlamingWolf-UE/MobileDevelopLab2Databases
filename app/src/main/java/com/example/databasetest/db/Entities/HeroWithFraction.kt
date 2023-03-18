package com.example.databasetest.db.Entities

import androidx.room.Embedded
import androidx.room.Relation

data class HeroWithFraction (
    @Embedded
    val hero: Hero,
    @Relation(
        parentColumn = "fraction_id",
        entityColumn = "fraction_id"
    )
    val fraction: Fraction
)