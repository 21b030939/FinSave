package com.example.finsave.data.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.finsave.data.model.dao.SpendingDao
import com.example.finsave.data.model.entity.Spending
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Spending::class], version = 1, exportSchema = false)
@TypeConverters(SpendingConverter::class)
abstract class SpendingsDatabase : RoomDatabase() {

    abstract fun spendingDao(): SpendingDao

    companion object {
        @Volatile
        private var INSTANCE: SpendingsDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): SpendingsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SpendingsDatabase::class.java,
                    "spendings_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
