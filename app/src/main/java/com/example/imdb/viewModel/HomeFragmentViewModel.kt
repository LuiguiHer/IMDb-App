package com.example.imdb.viewModel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imdb.adapters.HorizontalAdapterMovie
import com.example.imdb.model.ApiServices
import com.example.imdb.view.MovieDetailsFragment
import com.example.imdb.model.Movies
import com.example.imdb.model.RetrofitConfig.Companion.API_KEY
import com.example.imdb.model.RetrofitConfig.Companion.ENDPOINT_HOME
import com.example.imdb.model.RetrofitConfig.Companion.getRetrofit
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeFragmentViewModel: ViewModel() {
    private var horizontalAdapterMovie: HorizontalAdapterMovie? = null
    private val picasso = Picasso.get()


    val liveMoviesList = MutableLiveData<List<Movies>>()
    val liveShowMovies = MutableLiveData<Bundle>()
    val liveAdapter = MutableLiveData<HorizontalAdapterMovie>()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun getMovies(){
        CoroutineScope(Dispatchers.IO + coroutineExceptionHandler).launch {
            val call = getRetrofit.create(ApiServices::class.java).getMoviesHorizontal("${ENDPOINT_HOME}?api_key=${API_KEY}")
            val movies = call.body()
            if (call.isSuccessful){
                liveMoviesList.postValue(movies?.results)
            }else{
                errorMessage()
            }
        }
    }

    fun showMovies() {
        val listMovies: List<Movies> = liveMoviesList.value!!
        horizontalAdapterMovie = HorizontalAdapterMovie(picasso, listMovies) { movie ->
            val args = Bundle().apply {
                putParcelable(MovieDetailsFragment.ARG_MOVIE, movie)
            }
            liveShowMovies.value = args
        }
        liveAdapter.value = horizontalAdapterMovie
    }

    private fun errorMessage() {
        println("__Error to connect Retrofit__")
    }

}