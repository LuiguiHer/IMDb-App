package com.example.imdb.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.imdb.R
import com.example.imdb.databinding.FragmentSearchBinding
import com.example.imdb.viewModel.SearchFragmentViewModel


class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private val viewModel : SearchFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()

        viewModel.showMovies()

        viewModel.liveShowMovies.observe(this){ bundle ->
            findNavController().navigate(R.id.item_to_details_movies_series, bundle)
        }

        viewModel.liveAdapter.observe(this){ adapter ->
            binding.movieList.adapter = adapter
        }
    }

}