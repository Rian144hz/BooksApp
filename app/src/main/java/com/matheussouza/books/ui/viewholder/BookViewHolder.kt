package com.matheussouza.books.ui.viewholder

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.matheussouza.books.R
import com.matheussouza.books.databinding.ItemBookBinding
import com.matheussouza.books.entity.BookEntity
import com.matheussouza.books.ui.listenner.BookListenner

class BookViewHolder(private val item: ItemBookBinding, private val listenner: BookListenner) :
    RecyclerView.ViewHolder(item.root) {

    fun bind(book: BookEntity) {
        item.textTitle.text = book.title
        item.textAutor.text = book.autor
        item.textGenero.text = book.genero
        item.textTitle.setOnClickListener { listenner.onClick(book.id) }
        item.imageFavorite.setOnClickListener { listenner.onFavoriteClick(book.id) }

        setGeneroBackground(book.genero)
        updateFavoriteIcon(book.faorite)
    }

    private fun updateFavoriteIcon(favorite: Boolean) {
        val context = item.root.context
        val drawableRes = if (favorite) {
            R.drawable.ic_favorite_full
        } else {
            R.drawable.favorite_24dp_e3e3e3_fill0_wght400_grad0_opsz24
        }
        val drawable = ContextCompat.getDrawable(context, drawableRes)
        item.imageFavorite.setImageDrawable(drawable)
    }

    private fun setGeneroBackground(genero: String) {
        val context = item.root.context
        val backgroundRes = when (genero) {
            "Terror" -> R.drawable.rounde_label_red
            "Fantasia" -> R.drawable.rounded_label_fantasy
            else -> R.drawable.rounde_label_teal
        }
        val backgroundDrawable = ContextCompat.getDrawable(context, backgroundRes)
        item.textGenero.background = backgroundDrawable
    }


}
