package com.matheussouza.books.ui.adpter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.matheussouza.books.databinding.ItemBookBinding
import com.matheussouza.books.entity.BookEntity
import com.matheussouza.books.ui.listenner.BookListenner
import com.matheussouza.books.ui.viewholder.BookViewHolder

class BookAdpter : RecyclerView.Adapter<BookViewHolder>() {

    private var bookList: List<BookEntity> = listOf()
    private lateinit var bookListenner: BookListenner

    private var countCreate = 0
    private var bindCreate = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {

        val view = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(view,bookListenner)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {

        holder.bind(bookList[position])
    }

    fun uptadesBooks(list: List<BookEntity>) {
        bookList = list
        notifyDataSetChanged()

    }

    fun attachListenner(listenner: BookListenner){
        bookListenner = listenner
    }
}