package com.example.databasetest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.databasetest.db.Entities.*
import com.example.databasetest.db.Entities.Map
import java.util.concurrent.Executors

@Database(entities = [Hero::class, Fraction::class, Map::class, Match::class, HeroInMatch::class], version = 15)
abstract class MyDatabase : RoomDatabase() {

    abstract fun heroDao(): HeroDao
    abstract fun fractionDao(): FractionDao
    abstract fun mapDao(): MapDao
    abstract fun matchDao(): MatchDao


    companion object {


        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "my_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)

                        Executors.newSingleThreadExecutor().execute {
                            INSTANCE?.let {
                                it.fractionDao().insertFraction(Fraction(0, "Overwatch"))
                                it.fractionDao().insertFraction(Fraction(0, "Claw"))
                                it.fractionDao().insertFraction(Fraction(0, "None"))
                            }
                        }
                    }})
                    .build()

                INSTANCE = instance
                return instance
            }
        }
    }
}