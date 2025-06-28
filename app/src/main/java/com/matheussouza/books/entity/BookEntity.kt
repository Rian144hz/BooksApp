package com.matheussouza.books.entity

data class BookEntity(
    val id:Int,
    val title:String,
    val autor:String,
    var faorite:Boolean,
    val genero:String
)