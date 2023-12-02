package com.example.finsave.demo.model.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.finsave.demo.model.db.dao.SpendingDao
import com.example.finsave.demo.model.db.entity.Spending
import kotlinx.coroutines.flow.Flow

class Repository(private val spendingDao: SpendingDao) {

    val allSpendings: LiveData<List<Spending>> = spendingDao.getSpendingList()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(spending: Spending){
        spendingDao.insert(spending)
    }


}