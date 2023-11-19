package com.example.finsave.demo

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.finsave.demo.dao.SpendingDao
import com.example.finsave.demo.entity.Spending
import com.example.finsave.demo.repository.Repository
import kotlinx.coroutines.launch

class SpendingViewModel(private val repository:Repository):ViewModel() {
    val allSpendings: LiveData<List<Spending>> = repository.sortedByDateSpendings.asLiveData()

    fun insert (spending: Spending) = viewModelScope.launch {
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