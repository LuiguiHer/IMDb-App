package com.example.imdb.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.imdb.R
import com.example.imdb.databinding.FragmentProfileBinding
import com.example.imdb.model.ModelDatabase
import com.example.imdb.viewModel.ProfileFragmentViewModel

class ProfileFragment : Fragment() {
    private val viewModel : ProfileFragmentViewModel by viewModels()
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
        binding.itemNameUser.text = ModelDatabase.username
        viewModel.showCard()

        viewModel.liveBundle.observe(this){ bundle ->
            findNavController().navigate(R.id.item_to_details_movies_series, bundle)
        }
        viewModel.liveAdapter.observe(this){ adapter ->
            binding.reciclerViewCards.adapter = adapter
        }

    }

}