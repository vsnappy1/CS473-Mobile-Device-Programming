package com.kumar.mdp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kumar.mdp.model.Plant

@Database(entities = [Plant::class], version = 1, exportSchema = false)

abstract class PlantDatabase : RoomDatabase() {
    abstract fun plantDao(): PlantDao
    companion object {
        @Volatile
        private var INSTANCE: PlantDatabase? = null
        fun getDatabase(context: Context): PlantDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    PlantDatabase::class.java,
                    "plant_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}