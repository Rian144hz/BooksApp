package com.matheussouza.books.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.matheussouza.books.R
import com.matheussouza.books.ViewsModels.FavoriteViewModel
import com.matheussouza.books.databinding.FragmentFavoriteBinding
import com.matheussouza.books.helper.BookConstants
import com.matheussouza.books.ui.adpter.BookAdpter
import com.matheussouza.books.ui.listenner.BookListenner

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteViewModel by viewModels()
    private val adapter: BookAdpter = BookAdpter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        binding.recycleviewbooksFavorite.layoutManager = LinearLayoutManager(context)
        binding.recycleviewbooksFavorite.adapter = adapter

        attachListenner()
        setObservers()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavoriteBooks()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun attachListenner() {
        adapter.attachListenner(object : BookListenner {
            override fun onClick(id: Int) {
                val b = Bundle()
                b.putInt(BookConstants.KEY.BOOK_ID, id)
                findNavController().navigate(R.id.navigation_details, b)
            }

            override fun onFavoriteClick(id: Int) {
                viewModel.favorite(id)
                viewModel.getFavoriteBooks()
            }
        })
    }

    private fun setObservers() {
        viewModel.books.observe(viewLifecycleOwner) {
            if (it.isEmpty()) {
                binding.recycleviewbooksFavorite.visibility = View.GONE
                binding.textNobooks.visibility = View.VISIBLE
                binding.imageviewNobooks.visibility = View.VISIBLE
            } else {
                binding.recycleviewbooksFavorite.visibility = View.VISIBLE
                binding.textNobooks.visibility = View.GONE
                binding.imageviewNobooks.visibility = View.GONE
                adapter.uptadesBooks(it)
            }
        }
    }
}
