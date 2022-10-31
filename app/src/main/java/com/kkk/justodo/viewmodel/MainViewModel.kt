package com.kkk.justodo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kkk.justodo.model.Item
import com.kkk.justodo.model.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = Repository(application)

    var inputText = MutableLiveData<String?>(null)

    fun insert(i: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(i)
        }
    }

    fun update(i: Item){
        viewModelScope.launch(Dispatchers.IO){
            repository.update(i)
        }
    }

    fun delete(i: Item){
        viewModelScope.launch(Dispatchers.IO){
            repository.delete(i)
        }
    }

    fun getAll(): LiveData<List<Item>> {
        return repository.getAll()
    }
}