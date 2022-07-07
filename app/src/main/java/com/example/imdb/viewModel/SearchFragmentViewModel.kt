package com.example.imdb.viewModel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imdb.adapters.MovieAdapter
import com.example.imdb.model.ApiServices
import com.example.imdb.model.Movies
import com.example.imdb.model.RetrofitConfig
import com.example.imdb.view.MovieDetailsFragment
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchFragmentViewModel: ViewModel() {
    private var movieAdapter: MovieAdapter? = null
    private val picasso = Picasso.get()

    val liveBundle = MutableLiveData<Bundle>()
    val liveAdapter = MutableLiveData<MovieAdapter>()
    val liveMoviesList =MutableLiveData<List<Movies>>()
    val liveDataSearch = MutableLiveData<String>()

    fun getMovies(){
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitConfig.getRetrofit.create(ApiServices::class.java).getMovies("${RetrofitConfig.ENDPOINT_SEARCH}?api_key=${RetrofitConfig.API_KEY}")
            val movies = call.body()
            if (call.isSuccessful){
                liveMoviesList.postValue(movies?.items)
            }else{
                errorMessage()
            }
        }
    }

    fun getMovieSearch(query:String){
        CoroutineScope(Dispatchers.IO).launch {
            val call = RetrofitConfig.getRetrofit.create(ApiServices::class.java).getMovieSearched("${RetrofitConfig.ENDPOINT_RESULT}?api_key=${RetrofitConfig.API_KEY}&query=${query}")
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
        movieAdapter = MovieAdapter(picasso, listMovies) { movie ->
            val args = Bundle().apply {
                putParcelable(MovieDetailsFragment.ARG_MOVIE, movie)
            }
            liveBundle.value = args
            println(args)
        }
        liveAdapter.value = movieAdapter!!
    }

    private fun errorMessage() {
        println("__Error to connect Retrofit__")
    }

}


