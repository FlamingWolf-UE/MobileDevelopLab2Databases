package com.example.databasetest.repository

import android.app.Application
import android.os.AsyncTask
import androidx.annotation.UiThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.example.databasetest.db.Entities.*
import com.example.databasetest.db.MyDatabase

class FractionRepository(application: Application) {
    private var fractionDao: FractionDao
    private var fractionsList: LiveData<List<Fraction>>

    init {
        val db = MyDatabase.getDatabase(application)
        fractionDao = db.fractionDao()
        fractionsList = fractionDao.getAllFractions()
    }

    fun insert(fraction: Fraction) {
        InsertFractionAsyncTask(fractionDao).execute(fraction)
    }

    fun delete(fraction: Fraction) {
        DeleteFractionAsyncTask(fractionDao).execute(fraction)
    }

    fun update(fraction: Fraction) {
        UpdateFractionAsyncTask(fractionDao).execute(fraction)
    }

    fun getFractions(): LiveData<List<Fraction>>
    {
        return fractionsList
    }

    fun getFractionById(id : Int) : Fraction?
    {


        return GetFractionByIdAsyncTask(fractionDao).execute(id).get()
    }

    companion object {
        private class InsertFractionAsyncTask(private var fractionDao: FractionDao) :
            AsyncTask<Fraction, Void, Void>() {
            override fun doInBackground(vararg params: Fraction?): Void? {
                params[0]?.let { fractionDao.insertFraction(it) }
                return null
            }
        }


        private class GetFractionByIdAsyncTask(private var fractionDao: FractionDao) :
            AsyncTask<Int, Void, Fraction>() {
            override fun doInBackground(vararg params: Int?): Fraction? {
                params[0]?.let {  return fractionDao.getFractionById(it) }
                return null
            }
        }


        private class DeleteFractionAsyncTask(private var fractionDao: FractionDao) :
            AsyncTask<Fraction, Void, Void>() {
            override fun doInBackground(vararg params: Fraction?): Void? {
                params[0]?.let { fractionDao.deleteFraction(it) }
                return null
            }
        }

        private class UpdateFractionAsyncTask(private var fractionDao: FractionDao) :
            AsyncTask<Fraction, Void, Void>() {

            override fun doInBackground(vararg params: Fraction?): Void? {
                params[0]?.let { fractionDao.updateFraction(it) }
                return null
            }
        }

    }
}


