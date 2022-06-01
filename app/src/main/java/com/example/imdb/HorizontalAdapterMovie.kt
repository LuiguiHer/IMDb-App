package com.example.imdb

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.databinding.ItemMovieHorizontalBinding
import com.squareup.picasso.Picasso

class HorizontalAdapterMovie(

    private val picasso: Picasso,
    private val movieList: List<Movie>,
    private val onClick: (Movie) -> Unit
) : RecyclerView.Adapter<HorizontalAdapterMovie.MovieViewHolder>() {

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: HorizontalAdapterMovie.MovieViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HorizontalAdapterMovie.MovieViewHolder {
        val binding =
            ItemMovieHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    inner class MovieViewHolder(private val binding: ItemMovieHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(movie: Movie) {
            itemView.setOnClickListener {
                onClick(movie)
            }
            picasso.load(movie.imageUrl).into(binding.itemImage)
            binding.itemTitle.text = movie.title
            binding.itemRating.text = movie.rating
        }

    }

}