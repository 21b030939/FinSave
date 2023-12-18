package com.example.finsave.demo.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.finsave.demo.model.db.SpendingConverter
import com.example.finsave.demo.model.db.dao.SpendingDao
import com.example.finsave.demo.model.db.entity.Spending
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Spending::class], version = 1, exportSchema = false)
@TypeConverters(SpendingConverter::class)
abstract class SpendingsDatabase : RoomDatabase() {

    abstract fun spendingDao(): SpendingDao
    companion object {
        @Volatile
        private var INSTANCE: SpendingsDatabase? = null

        fun getDatabase(context: Context,
                        scope: CoroutineScope
        ): SpendingsDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SpendingsDatabase::class.java,
                    "spendings_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class SpendingDatabaseCallback(
        private val scope: CoroutineScope
    ):RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {

                }
            }
        }

        suspend fun populateDatabase(spendingDao: SpendingDao) {
            spendingDao.deleteAll()
        }
    }
}
