package com.example.imdb

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class MovieAdapter(
    private val picasso: Picasso,
    private val movieList: List<Movie>,
    private val onClick: (Movie) -> Unit
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
        fun bind(movie: Movie) {
            itemView.setOnClickListener {
                onClick(movie)
            }
            picasso.load(movie.imageUrl).into(binding.movieImg)
            binding.movieYear.text = movie.year
            binding.movieActors.text = movie.principal_actors[0] +", "+ movie.principal_actors[1]
            binding.movieTitle.text = movie.title
        }

    }
}