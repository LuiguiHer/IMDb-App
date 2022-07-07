package com.example.imdb.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.imdb.R
import com.example.imdb.databinding.FragmentMovieDetailsBinding
import com.example.imdb.model.Movies
import com.example.imdb.model.RetrofitConfig.Companion.URL_IMAGE
import com.squareup.picasso.Picasso


class MovieDetailsFragment : Fragment() {
    private lateinit var movie: Movies
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movie = it.getParcelable(ARG_MOVIE)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonBack.setOnClickListener {
            onDestroy()

            findNavController().navigate(R.id.homeFragment)
        }
        initializeView()
    }

    @SuppressLint("SetTextI18n")
    private fun initializeView() {
        val picasso = Picasso.get()
        binding.itemSubTitle.text = movie.title
        binding.itemTitle.text = movie.title
        binding.itemTitleOriginal.text = movie.original_title
        binding.itemYear.text = " - " + movie.release_date
        binding.itemRating.text = movie.vote_average.toString()
        picasso.load(URL_IMAGE+movie.backdrop_path).into(binding.itemVideo)
        picasso.load(URL_IMAGE+movie.poster_path).into(binding.itemImage)
        binding.itemSynopsis.text = movie.overview
        binding.containerChapters.visibility = View.GONE
    }

    companion object {
        const val ARG_MOVIE = "arg_movie"
    }
}

