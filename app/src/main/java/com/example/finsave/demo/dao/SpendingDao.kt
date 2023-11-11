package com.example.finsave.demo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.finsave.demo.entity.Spending
import kotlinx.coroutines.flow.Flow

@Dao
interface SpendingDao {
    @Query("SELECT * FROM spending_table")
    fun getSpendingList():List<Spending> //get list of all spendings

    @Query("SELECT * FROM spending_table ORDER BY purchaseDate")
    fun getSortedByDateSpendings(): Flow<List<Spending>> //get list of spendings sorted by date

    @Query("SELECT * FROM spending_table ORDER BY category")
    fun getSortedByCategorySpendings(): Flow<List<Spending>> //get list of spendings sorted by category

    @Query("SELECT * FROM spending_table ORDER BY price")
    fun getSortedByPriceSpendings(): Flow<List<Spending>> //get list of spendings sorted by price

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(spending: Spending) //inserting spending to db

    @Query("DELETE FROM spending_table")
    suspend fun deleteAll()

}