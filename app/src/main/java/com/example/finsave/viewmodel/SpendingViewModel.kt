package com.example.finsave.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.finsave.SpendingApplication
import com.example.finsave.demo.model.db.SpendingsDatabase
import com.example.finsave.demo.model.db.entity.Spending
import com.example.finsave.demo.model.repository.Repository
import kotlinx.coroutines.launch

class SpendingViewModel(private val repository: Repository) : ViewModel() {

    val allSpending: LiveData<List<Spending>> = repository.allSpendings

    fun insert(spending: Spending) = viewModelScope.launch{
        repository.insert(spending)
    }

}

class SpendingViewModelFactory(private val repository: Repository):ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(SpendingViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return SpendingViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}