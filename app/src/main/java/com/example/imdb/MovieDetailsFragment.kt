package com.example.imdb

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.imdb.databinding.FragmentMovieDetailsBinding
import com.squareup.picasso.Picasso



class MovieDetailsFragment : Fragment() {
    private lateinit var movie: Movie
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
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }
        initializeView()
    }

    @SuppressLint("SetTextI18n")
    private fun initializeView(){
        val picasso = Picasso.get()
        binding.itemTitleOne.text = movie.subTitle
        binding.itemTitleBig.text= movie.title
        binding.itemTitleOriginal.text = movie.titleOrigin
        binding.itemYear.text = " - "+movie.year
        binding.itemType.text = movie.type
        binding.itemRating.text = movie.rating
        picasso.load(movie.videoUrl).into(binding.itemVideo)
        picasso.load(movie.imageUrl).into(binding.itemImage)
        binding.itemGender.text = movie.genders[0]
        binding.itemSynopsis.text = movie.synopsis
        if (movie.chapters == null){
            binding.containerChapters.visibility = View.GONE
        } else {
            binding.chaptersNumber.text = movie.chapters.toString()
        }

    }

    companion object {
        const val ARG_MOVIE = "arg_movie"
    }
}

