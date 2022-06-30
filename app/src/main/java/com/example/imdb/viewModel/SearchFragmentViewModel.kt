package com.example.imdb.viewModel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imdb.HorizontalAdapterMovie
import com.example.imdb.MovieAdapter
import com.example.imdb.model.MovieHelper
import com.example.imdb.view.MovieDetailsFragment
import com.squareup.picasso.Picasso

class SearchFragmentViewModel: ViewModel() {
    private  var listMovies = MovieHelper().movieList()
    private var movieAdapter: MovieAdapter? = null
    private val picasso = Picasso.get()

    val liveShowMovies = MutableLiveData<Bundle>()
    val liveAdapter = MutableLiveData<MovieAdapter>()

    fun showMovies() {
        movieAdapter = MovieAdapter(picasso, listMovies) { movie ->
            val args = Bundle().apply {
                putParcelable(MovieDetailsFragment.ARG_MOVIE, movie)
            }
            liveShowMovies.value = args
            println(args)
        }
        liveAdapter.value = movieAdapter
    }
}