package com.matheussouza.books.ui.listenner

interface BookListenner {
    fun onClick(id: Int)
    fun onFavoriteClick(id: Int)
}