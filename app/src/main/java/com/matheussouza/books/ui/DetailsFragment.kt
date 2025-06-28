package com.matheussouza.books.ui

import android.content.DialogInterface
import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.matheussouza.books.R
import com.matheussouza.books.ViewsModels.DetailsViewModel
import com.matheussouza.books.databinding.FragmentDetailsBinding
import com.matheussouza.books.helper.BookConstants

class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailsViewModel by viewModels()
    private var bookId = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)

        bookId = arguments?.getInt(BookConstants.KEY.BOOK_ID) ?: 0

        setListeners()
        setObservers()
        viewModel.getBookById(bookId)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListeners() {
        binding.imageBack.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
        }
        binding.buttonRemove.setOnClickListener { handleRemove() }
        binding.CheckBox.setOnClickListener{ handleFavorite()}
    }
    private fun handleFavorite(){
        viewModel.favorite(bookId)
    }
    private fun handleRemove() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage(getString(R.string.dialog_message_delete_item))
            .setPositiveButton(
                getString(R.string.dialog_positive_button_yes),
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        viewModel.deleteBook(bookId)
                    }
                })
            .setNegativeButton(
                getString(R.string.dialog_negative_button_no),
                object : DialogInterface.OnClickListener {
                    override fun onClick(dialog: DialogInterface?, which: Int) {
                        dialog?.dismiss()
                    }

                })
        builder.create().show()
    }

    private fun setObservers() {
        viewModel.book.observe(viewLifecycleOwner) { book ->
            binding.textAutores.text = book.title
            binding.textAutor2.text = book.autor
            binding.textGeneros.text = book.genero
            binding.CheckBox.isChecked = book.faorite
            setGeneroBackground(book.genero)
            viewModel.bookRemove.observe(viewLifecycleOwner) {
                if (it) {
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.msg_removed_successfully),
                        Toast.LENGTH_SHORT
                    ).show()
                    requireActivity().supportFragmentManager.popBackStack()
                }
            }
        }
    }

    private fun setGeneroBackground(genero: String) {
        val context = binding.root.context
        val backgroundRes = when (genero) {
            "Terror" -> R.drawable.rounde_label_red
            "Fantasia" -> R.drawable.rounded_label_fantasy
            else -> R.drawable.rounde_label_teal
        }
        val backgroundDrawable = ContextCompat.getDrawable(context, backgroundRes)
        binding.textGeneros.background = backgroundDrawable
    }
}
