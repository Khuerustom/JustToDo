package com.kkk.justodo.viewmodel

import androidx.lifecycle.*
import com.kkk.justodo.model.Item
import com.kkk.justodo.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository) : ViewModel() {

    val allItems: LiveData<List<Item>> = repository.allItems.asLiveData()
    val _allItems = repository._allItems
    var isUpdateEnabled: Boolean = false
    var selectedItemId: Int = 0

    init {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }

    var inputText = MutableLiveData<String?>(null)

    fun insertOrUpdate() {
        when (isUpdateEnabled) {
            true -> viewModelScope.launch(Dispatchers.IO) {
                repository.update(Item(selectedItemId, inputText.value.toString()))
                isUpdateEnabled = false
                selectedItemId = 0
            }
            false -> viewModelScope.launch(Dispatchers.IO) {
                repository.insert(Item(inputText.value.toString()))
            }
        }
    }

    fun insert() {
        viewModelScope.launch(Dispatchers.IO) {

            repository.insert(Item(inputText.value.toString()))
        }
    }

    fun update(i: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(i)
        }
    }

    fun delete(i: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(i)
        }
    }

}

class MainViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}