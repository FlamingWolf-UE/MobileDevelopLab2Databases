package com.example.databasetest.db.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fraction")
data class Fraction(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "fraction_id")
    val id: Int = 0,
    @ColumnInfo(name="fraction_name")
    val name :String

)
{
    override fun toString(): String {
        return name
    }
}

