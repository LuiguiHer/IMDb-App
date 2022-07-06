package com.example.imdb.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.databinding.ItemMovieHorizontalBinding
import com.example.imdb.model.Movies
import com.example.imdb.model.RetrofitConfig.Companion.URL_IMAGE
import com.squareup.picasso.Picasso


class HorizontalAdapterMovie(

    private val picasso: Picasso,
    private val moviesList: List<Movies>,
    private val onClick: (Movies) -> Unit
) : RecyclerView.Adapter<HorizontalAdapterMovie.MovieViewHolder>() {

    override fun getItemCount(): Int = moviesList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val binding =
            ItemMovieHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    inner class MovieViewHolder(private val binding: ItemMovieHorizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(movie: Movies) {
            itemView.setOnClickListener {
                onClick(movie)
            }

            picasso.load(URL_IMAGE+movie.poster_path).into(binding.itemImage)
            binding.itemTitle.text = movie.original_title
            binding.itemRating.text = movie.vote_average.toString()
        }
    }
}