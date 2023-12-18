package com.example.finsave.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.finsave.data.model.entity.Spending
import com.example.finsave.data.repository.Repository
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