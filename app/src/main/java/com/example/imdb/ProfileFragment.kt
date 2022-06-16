package com.example.imdb

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.imdb.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {
    private var cardsAdapter: CardsAdapter? = null
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val userName = (activity as AfterLoginActivity).userName
        binding.itemNameUser.text = userName
        showCard(cardsList())
    }

    private fun showCard(cardsProfile: List<CardsProfile>) {
        cardsAdapter = CardsAdapter(cardsProfile) { movie ->
            val args = Bundle().apply {
                putParcelable(MovieDetailsFragment.ARG_MOVIE, movie)
            }
            findNavController().navigate(R.id.item_to_details_movies_series, args)
        }
        binding.reciclerViewCards.adapter = cardsAdapter
    }

    private fun cardsList(): List<CardsProfile> {
        return listOf(
            CardsProfile("Calificaciones", "Calificar y obtener recomendaciones", 0),
            CardsProfile("Listas", "Agregar a Listas", 4),
            CardsProfile("Comentarios", "Aun sin comentarios", 0),
        )
    }
}