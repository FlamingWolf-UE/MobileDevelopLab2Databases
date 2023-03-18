package com.example.databasetest.db.Entities

import android.service.autofill.FieldClassification
import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class HeroAndMatches(
    @Embedded
    val heroWithFraction: Hero,
    @Relation(
        parentColumn = "idhero",
        entityColumn = "match_id",
        associateBy = Junction(HeroInMatch::class)
    )
    val matchesWithMaps: List<Match>
)
