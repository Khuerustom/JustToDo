package com.kkk.justodo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    var body: String = "Default",
    var isImportant: Boolean = false,
    var isDone:Boolean  = false
){

    constructor(
        justBody: String
    ): this(body = justBody)

    constructor( id: Int): this(id = id, body ="Dummy")

    constructor(): this(id=0)
}
