package com.example.databasetest.db.Entities

import androidx.room.*

    @Entity(tableName = "hero")
    data class Hero(

        @ColumnInfo(name = "idhero")
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,

        @ColumnInfo(name = "hero_name")
        val name: String,

        @ColumnInfo(name = "hero_HP")
        val hp: Int,

        @ColumnInfo(name = "hero_max_primary_damage")
        val maxPrimaryDamage: Int,


        @ColumnInfo(name = "hero_description")
        val description: String,

        @ColumnInfo(name = "fraction_id")
        val fraction_id: Int

    )

