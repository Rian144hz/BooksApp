package com.matheussouza.books.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.matheussouza.books.R
import com.matheussouza.books.ViewsModels.HomeViewModel
import com.matheussouza.books.databinding.FragmentHomeBinding
import com.matheussouza.books.helper.BookConstants
import com.matheussouza.books.ui.adpter.BookAdpter
import com.matheussouza.books.ui.listenner.BookListenner

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val ViewModel: HomeViewModel by viewModels()
    private val adpter: BookAdpter = BookAdpter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.recycleviewbooks.layoutManager = LinearLayoutManager(context)
        binding.recycleviewbooks.adapter = adpter

        attachListenner()


        setObservers()

        val root: View = binding.root
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        ViewModel.getAllBooks()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun attachListenner(){
        adpter.attachListenner(object : BookListenner {
            override fun onClick(id: Int) {
               val b = Bundle()
                b.putInt(BookConstants.KEY.BOOK_ID,id)



                findNavController().navigate(R.id.navigation_details, b)
            }

            override fun onFavoriteClick(id: Int) {
               ViewModel.favorite(id)
                ViewModel.getAllBooks()
            }

        })


    }
    private fun setObservers() {
        ViewModel.books.observe(viewLifecycleOwner) {
            adpter.uptadesBooks(it)
        }
    }
}