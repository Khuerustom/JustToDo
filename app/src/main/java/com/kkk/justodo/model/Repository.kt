package com.kkk.justodo.model

import android.app.Application
import androidx.lifecycle.LiveData

class Repository(application: Application) {

    private val db = Database.getDatabase(application)
    private val dao = db.getDao()

    suspend fun insert(i:Item){
        dao.insert(i)
    }
    suspend fun update(i:Item){
        dao.update(i)
    }
    suspend fun delete(i:Item){
        dao.delete(i)
    }

    fun getAll(): LiveData<List<Item>> {
        return dao.getAll()
    }

}