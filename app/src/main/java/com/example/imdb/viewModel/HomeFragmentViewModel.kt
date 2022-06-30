package com.example.imdb.viewModel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imdb.HorizontalAdapterMovie
import com.example.imdb.view.MovieDetailsFragment
import com.example.imdb.model.MovieHelper
import com.squareup.picasso.Picasso

class HomeFragmentViewModel: ViewModel() {
    private var horizontalAdapterMovie: HorizontalAdapterMovie? = null
    private val picasso = Picasso.get()
    private val listMovies = MovieHelper().movieList()

    val liveShowMovies = MutableLiveData<Bundle>()
    val liveAdapter = MutableLiveData<HorizontalAdapterMovie>()

    fun showMovies() {
        horizontalAdapterMovie = HorizontalAdapterMovie(picasso, listMovies) { movie ->
            val args = Bundle().apply {
                putParcelable(MovieDetailsFragment.ARG_MOVIE, movie)
            }
            liveShowMovies.value = args
            println(args)
        }
        liveAdapter.value = horizontalAdapterMovie
    }
}