package com.example.imdb.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.databinding.ItemMovieBinding
import com.example.imdb.model.Movies
import com.example.imdb.model.RetrofitConfig.Companion.URL_IMAGE
import com.squareup.picasso.Picasso

class MovieAdapter(
    private val picasso: Picasso,
    private val movieList: List<Movies>,
    private val onClick: (Movies) -> Unit
): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding)
    }


    inner  class MovieViewHolder(private val binding: ItemMovieBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(movie: Movies) {
            itemView.setOnClickListener {
                onClick(movie)
            }
            picasso.load(URL_IMAGE+movie.poster_path).into(binding.movieImg)
            binding.movieYear.text = movie.release_date
            binding.movieTitle.text = movie.original_title
        }
    }
}