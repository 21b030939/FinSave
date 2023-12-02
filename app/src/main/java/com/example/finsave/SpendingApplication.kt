package com.example.finsave

import android.app.Application
import com.example.finsave.demo.model.db.SpendingsDatabase
import com.example.finsave.demo.model.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SpendingApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy{ SpendingsDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { Repository(database.spendingDao()) }

}