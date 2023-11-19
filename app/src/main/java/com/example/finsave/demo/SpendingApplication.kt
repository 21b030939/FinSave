package com.example.finsave.demo

import android.app.Application
import com.example.finsave.demo.db.SpendingsDatabase
import com.example.finsave.demo.repository.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class SpendingApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy{ SpendingsDatabase.getDatabase(this) }
    val repository by lazy { Repository(database.spendingDao()) }

}