package com.example.imdb.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.imdb.R
import com.example.imdb.databinding.FragmentHomeBinding
import com.example.imdb.model.Movie
import com.example.imdb.model.MovieHelper
import com.example.imdb.viewModel.HomeFragmentViewModel
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val picasso = Picasso.get()
    private val binding get() = _binding!!
    private val viewModel: HomeFragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.showMovies()
        viewModel.liveShowMovies.observe(this){
            findNavController().navigate(R.id.item_to_details_movies_series, it)
        }
        viewModel.liveAdapter.observe(this){ adapter ->
            binding.reciclerViewHome.adapter = adapter
        }
        firstMovie(MovieHelper().movieList()[7])
    }

    private fun firstMovie(movie: Movie) {
        binding.movieTitle.text = movie.title
        picasso.load(movie.videoUrl).into(binding.movieVideo)
        picasso.load(movie.imageUrl).into(binding.movieImg)
    }

}