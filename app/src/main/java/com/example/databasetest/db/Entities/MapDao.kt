package com.example.databasetest.db.Entities

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MapDao {
    // Map queries
    @Query("SELECT * FROM map")
    fun getAllHeroes(): LiveData<List<Map>>

    @Query("SELECT * FROM map WHERE map_id =:mapId")
    fun getHeroById(mapId: Int): Map?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMap(map: Map)

    @Update
    fun updateMap(map: Map)

    @Delete
    fun deleteMap(map: Map)
}