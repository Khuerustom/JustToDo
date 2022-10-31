package com.kkk.justodo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var body: String,
    var isImportant: Int = 0,
    var isDone: Int = 0
){

    constructor(
        body: String
    ): this(0, body = body, 0, 0)

    constructor( id: Int): this(id, "Dummy", 0, 0)
}
