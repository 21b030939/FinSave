package com.example.finsave.demo.repository

import androidx.annotation.WorkerThread
import com.example.finsave.demo.dao.SpendingDao
import com.example.finsave.demo.entity.Spending
import kotlinx.coroutines.flow.Flow

class Repository(private val spendingDao: SpendingDao) {

    val sortedByDateSpendings: Flow<List<Spending>> = spendingDao.getSortedByDateSpendings()

    val sortedByCategorySpendings: Flow<List<Spending>> = spendingDao.getSortedByCategorySpendings()

    val sortedByPriceSpendings: Flow<List<Spending>> = spendingDao.getSortedByPriceSpendings()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(spending:Spending){
        spendingDao.insert()
    }


}