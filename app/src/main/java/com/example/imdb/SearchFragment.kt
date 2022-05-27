package com.example.imdb

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imdb.databinding.FragmentSearchBinding
import com.squareup.picasso.Picasso


class SearchFragment : Fragment() {
    private var movieAdapter: MovieAdapter? = null
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        showMovies(movieList())
    }

    private fun showMovies(movieList: List<Movie>){
        if ( movieAdapter == null){
            val picasso = Picasso.get()
            movieAdapter = MovieAdapter(picasso,movieList){ movie ->

            }
            binding.movieList.adapter = movieAdapter

        }
    }

    private fun movieList(): List<Movie> {
        return listOf(
            Movie("Encanto","Encanto","movie",2022, listOf("familia","caricatura"),4,"Una joven colombiana puede ser la última esperanza para su familia, cuando descubre que la magia que rodea a Encanto, un lugar encantado que bendice a los niños con dones únicos, se encuentra en serio peligro","https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSsifNBbCd9akddbx7-cIBMNVyZ2TXCR5ptbIx9M9_lt0KhRp6M","https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSsifNBbCd9akddbx7-cIBMNVyZ2TXCR5ptbIx9M9_lt0KhRp6M",null),
            Movie("Marvel","Marvel","movie",2022, listOf("accion","aventura"),4,"Una joven colombiana puede ser la última esperanza para su familia, cuando descubre que la magia que rodea a Encanto, un lugar encantado que bendice a los niños con dones únicos, se encuentra en serio peligro","https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSsifNBbCd9akddbx7-cIBMNVyZ2TXCR5ptbIx9M9_lt0KhRp6M","https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSsifNBbCd9akddbx7-cIBMNVyZ2TXCR5ptbIx9M9_lt0KhRp6M",null),
            Movie("SpiderMan","SpiderMan","movie",2022, listOf("ficcion","aventura"),4,"Una joven colombiana puede ser la última esperanza para su familia, cuando descubre que la magia que rodea a Encanto, un lugar encantado que bendice a los niños con dones únicos, se encuentra en serio peligro","https://mx.web.img3.acsta.net/pictures/21/11/25/18/23/3142881.jpg","https://mx.web.img3.acsta.net/pictures/21/11/25/18/23/3142881.jpg",null),
            Movie("Red","Red","movie",2022, listOf("infantil","drama"),4,"Una joven colombiana puede ser la última esperanza para su familia, cuando descubre que la magia que rodea a Encanto, un lugar encantado que bendice a los niños con dones únicos, se encuentra en serio peligro","https://static.wikia.nocookie.net/disney/images/b/be/Turning_Red_Poster.jpg/revision/latest?cb=20220117230032&path-prefix=es","https://pics.filmaffinity.com/Red-899479977-large.jpg",null),
            Movie("Moonfall","MoonFall","movie",2022, listOf("accion","ficcion"),4,"Una joven colombiana puede ser la última esperanza para su familia, cuando descubre que la magia que rodea a Encanto, un lugar encantado que bendice a los niños con dones únicos, se encuentra en serio peligro","https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcSsifNBbCd9akddbx7-cIBMNVyZ2TXCR5ptbIx9M9_lt0KhRp6M","https://pics.filmaffinity.com/Moonfall-138176660-large.jpg",null)
        )
    }

}