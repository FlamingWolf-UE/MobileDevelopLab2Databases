package com.example.databasetest.repository

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.databasetest.db.Entities.Hero
import com.example.databasetest.db.Entities.HeroDao
import com.example.databasetest.db.Entities.HeroWithFraction
import com.example.databasetest.db.MyDatabase

class HeroRepository(application: Application) {
    private  var heroDao : HeroDao



    init {
        val db = MyDatabase.getDatabase(application)
        heroDao = db.heroDao()



    }

    fun insert(hero : Hero)
    {
        InsertHeroAsyncTask(heroDao).execute(hero)
    }

    fun delete(hero : Hero)
    {
        DeleteHeroAsyncTask(heroDao).execute(hero)
    }

    fun update(hero : Hero)
    {
        UpdateHeroAsyncTask(heroDao).execute(hero)
    }

    fun getHeroes() : LiveData<List<HeroWithFraction>>
    {
        return heroDao.getHeroesByFraction()
    }

    fun getItemsFiltered(filter: String): LiveData<List<HeroWithFraction>> {
        return heroDao.getItemsFiltered(filter)
    }



    companion object {
        private class InsertHeroAsyncTask(private var heroDao: HeroDao) :
            AsyncTask<Hero, Void, Void>()
        {

            override fun doInBackground(vararg params: Hero?): Void? {
                params[0]?.let { heroDao.insertHero(it) }
                return null
            }
        }

        private class DeleteHeroAsyncTask(private var heroDao: HeroDao) :
            AsyncTask<Hero, Void, Void>()
        {

            override fun doInBackground(vararg params: Hero?): Void? {
                params[0]?.let { heroDao.deleteHero(it) }
                return null
            }
        }

        private class UpdateHeroAsyncTask(private var heroDao: HeroDao) :
            AsyncTask<Hero, Void, Void>()
        {

            override fun doInBackground(vararg params: Hero?): Void? {
                params[0]?.let { heroDao.updateHero(it) }
                return null
            }
        }

    }




}