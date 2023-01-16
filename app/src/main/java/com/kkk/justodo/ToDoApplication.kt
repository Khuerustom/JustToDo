package com.kkk.justodo

import android.app.Application
import com.kkk.justodo.model.Database
import com.kkk.justodo.model.Repository

class ToDoApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    private val database by lazy { Database.getDatabase(this) }
    val repository by lazy { Repository(database.getDao()) }
}