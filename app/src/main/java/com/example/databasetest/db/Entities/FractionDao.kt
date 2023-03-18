package com.example.databasetest.db.Entities

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FractionDao {



        // Fraction queries
        @Query("SELECT * FROM fraction")
        fun getAllFractions(): LiveData<List<Fraction>>

        @Query("SELECT * FROM fraction WHERE fraction_id = :fractionId")
        fun getFractionById(fractionId: Int): Fraction?

        @Transaction
        @Query("SELECT * FROM fraction")
        fun getFractionWithHeroes() : LiveData<List<FractionWithHeroesOf>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
         fun insertFraction(fraction: Fraction)

        @Update
         fun updateFraction(fraction: Fraction)

        @Delete
         fun deleteFraction(fraction: Fraction)



}