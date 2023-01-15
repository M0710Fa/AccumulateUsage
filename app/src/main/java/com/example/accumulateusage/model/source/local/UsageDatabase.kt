package com.example.accumulateusage.model.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Usage::class], version = 0)
abstract class UsageDatabase: RoomDatabase() {
    abstract fun usageDao(): UsageDao

    @Volatile
    private var INSTANCE: UsageDatabase? = null

    fun getDatabase(context: Context): UsageDatabase {
        return INSTANCE ?: synchronized(this){
            val instance = Room.databaseBuilder(
                context.applicationContext,
                UsageDatabase::class.java,
                "usage_database"
            )
                .fallbackToDestructiveMigration()
                .build()
            INSTANCE = instance
            return instance
        }
    }
}