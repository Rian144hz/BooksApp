package com.matheussouza.books.ViewsModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matheussouza.books.entity.BookEntity
import com.matheussouza.books.repository.BookRepository

class DetailsViewModel : ViewModel() {
    private val repository: BookRepository = BookRepository.getInstance()

    private val _book = MutableLiveData<BookEntity>()
    val book: LiveData<BookEntity> = _book

    private val _bookRemove = MutableLiveData<Boolean>()
    val bookRemove: LiveData<Boolean> = _bookRemove


    fun getBookById(id: Int) {
        _book.value = repository.getBookyId(id)
    }

    fun deleteBook(id: Int){
       _bookRemove.value = repository.deleteBooks(id)

    }
    fun favorite(id: Int){
        repository.tuggleFavoriteStattus(id)
    }
}
