package com.matheussouza.books.ViewsModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matheussouza.books.entity.BookEntity
import com.matheussouza.books.repository.BookRepository

class HomeViewModel : ViewModel() {

    private val _books = MutableLiveData<List<BookEntity>>().apply {
    }
    val books: MutableLiveData<List<BookEntity>> = _books

    private val repository = BookRepository.getInstance()

    fun getAllBooks() {
     _books.value = repository.getAllBooks()
    }
    fun favorite(id: Int){
        repository.tuggleFavoriteStattus(id)
    }
}