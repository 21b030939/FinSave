package com.example.finsave.demo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.finsave.demo.entity.Spending

@Database(entities = arrayOf(Spending::class), version = 1, exportSchema = false)
abstract class SpendingsDatabase:RoomDatabase() {
    companion object{
        @Volatile
        private var INSTANCE:SpendingsDatabase? = null

        fun getDatabase(context: Context): SpendingsDatabase{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SpendingsDatabase::class.java,
                    "spendings_database"
                ).build()
                INSTANCE=instance
                instance
            }
        }
    }
}