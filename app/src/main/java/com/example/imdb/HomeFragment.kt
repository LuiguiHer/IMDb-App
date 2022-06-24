package com.example.imdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.imdb.databinding.FragmentHomeBinding
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {

    private var horizontalAdapterMovie: HorizontalAdapterMovie? = null
    private var _binding: FragmentHomeBinding? = null
    private val picasso = Picasso.get()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        firstMovie(MovieHelper().movieList()[7])
        showMovies(MovieHelper().movieList())
    }

    private fun firstMovie(movie: Movie) {
        binding.movieTitle.text = movie.title
        picasso.load(movie.videoUrl).into(binding.movieVideo)
        picasso.load(movie.imageUrl).into(binding.movieImg)
    }

    private fun showMovies(movieList: List<Movie>) {
        horizontalAdapterMovie = HorizontalAdapterMovie(picasso, movieList) { movie ->
            val args = Bundle().apply {
                putParcelable(MovieDetailsFragment.ARG_MOVIE, movie)
            }
            findNavController().navigate(R.id.item_to_details_movies_series, args)
        }
        binding.reciclerViewHome.adapter = horizontalAdapterMovie
    }

}