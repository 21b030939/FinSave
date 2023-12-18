package com.example.finsave.data.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.finsave.data.model.dao.SpendingDao
import com.example.finsave.data.model.entity.Spending
import kotlinx.coroutines.flow.Flow

class Repository(private val spendingDao: SpendingDao) {

    val allSpendings: LiveData<List<Spending>> = spendingDao.getSpendingList()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(spending: Spending){
        spendingDao.insert(spending)
    }


}