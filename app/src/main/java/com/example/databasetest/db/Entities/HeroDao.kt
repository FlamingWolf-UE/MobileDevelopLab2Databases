package com.example.databasetest.db.Entities

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface HeroDao {


        // Hero queries
        @Query("SELECT * FROM hero")
        fun getAllHeroes(): LiveData<List<Hero>>

        @Query("SELECT * FROM hero WHERE idhero =:heroId")
        fun getHeroById(heroId: Int): Hero?

        @Transaction
        @Query("SELECT * FROM hero")
        fun getHeroesByFraction(): LiveData<List<HeroWithFraction>>

        @Transaction
        @Query("SELECT * FROM hero WHERE fraction_id = :id")
        fun getHeroesByFractions(id : Int): LiveData<List<HeroWithFraction>>

        @Transaction
        @Query("SELECT * FROM hero")
        fun getHeroesWithMatchesAndFractions(): LiveData<List<HeroAndMatches>>

        @Transaction
        @Query("SELECT * FROM hero")
        fun getHeroesWithMatchesAndMaps(): LiveData<List<HeroWithFractionWithMatchesWithMap>>

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        fun insertHero(hero: Hero)

        @Update
        fun updateHero(hero: Hero)

        @Delete
        fun deleteHero(hero: Hero)

        @Query("SELECT * from hero WHERE hero_name LIKE :filter")
        fun getItemsFiltered(filter: String): LiveData<List<HeroWithFraction>>


}