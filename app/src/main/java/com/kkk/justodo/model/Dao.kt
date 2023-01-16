package com.kkk.justodo.model

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@androidx.room.Dao
interface Dao {
    @Insert
    suspend fun insert(i: Item)

    @Update
    suspend fun update(i: Item)

    @Delete
    suspend fun delete(i: Item)

    @Query("SELECT * FROM todos")
    fun getAll(): Flow<List<Item>>

    @Query("SELECT * FROM todos")
    fun _getAll(): LiveData<List<Item>>

    @Query("DELETE FROM todos")
    suspend fun deleteAll()
}