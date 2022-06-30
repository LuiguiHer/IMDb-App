package com.example.imdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.imdb.MovieDetailsFragment.Companion.ARG_MOVIE
import com.example.imdb.databinding.FragmentSearchBinding
import com.squareup.picasso.Picasso


class SearchFragment : Fragment() {
    private  var listMovies = MovieHelper().movieList()
    private var movieAdapter: MovieAdapter? = null
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

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
        showMovies(listMovies)
    }

    private fun showMovies(movieList: List<Movie>) {
        val picasso = Picasso.get()
        movieAdapter = MovieAdapter(picasso, movieList) { movie ->
            val args = Bundle().apply {
                putParcelable(ARG_MOVIE, movie)
            }
            findNavController().navigate(R.id.item_to_details_movies_series, args)
        }
        binding.movieList.adapter = movieAdapter
    }
}