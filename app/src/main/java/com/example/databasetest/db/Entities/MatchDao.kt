package com.example.databasetest.db.Entities

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MatchDao {

    // Match queries
    @Query("SELECT * FROM match")
    fun getAllMatches(): LiveData<List<Match>>

    @Query("SELECT * FROM match WHERE match_id =:matchId")
    fun getMatchById(matchId: Int): Match?

    @Transaction
    @Query("SELECT * FROM match")
    fun getMatchWithMap(): LiveData<List<MatchWithMap>>

    @Transaction
    @Query("SELECT * FROM match")
    fun getMatchAndHeroes(): LiveData<List<MatchAndHeroes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun assignMatchWithHero(heroInMatch : HeroInMatch)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMatch(match: Match)

    @Update
    fun updateMatch(match: Match)

    @Delete
    fun deleteMatch(match: Match)
}