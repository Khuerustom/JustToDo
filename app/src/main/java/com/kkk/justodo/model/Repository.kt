package com.kkk.justodo.model

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class Repository(private val dao: Dao) {

    val allItems : Flow<List<Item>> = dao.getAll()
    val _allItems = dao._getAll()


    @WorkerThread
    suspend fun insert(i:Item){
        dao.insert(i)
    }


    @WorkerThread
    suspend fun update(i:Item){
        dao.update(i)
    }


    @WorkerThread
    suspend fun delete(i:Item){
        dao.delete(i)
    }

    
    @WorkerThread
    suspend fun deleteAll(){
        dao.deleteAll()
    }

}