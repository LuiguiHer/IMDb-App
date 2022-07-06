package com.example.imdb.viewModel

import android.os.Bundle
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imdb.adapters.CardsAdapter
import com.example.imdb.model.CardsProfileHelper
import com.example.imdb.view.MovieDetailsFragment

class ProfileFragmentViewModel: ViewModel() {
    private val items = CardsProfileHelper.listCardItems
    private var cardsAdapter: CardsAdapter? = null

    val liveBundle =MutableLiveData<Bundle>()
    val liveAdapter = MutableLiveData<CardsAdapter>()

    fun showCard() {
        cardsAdapter = CardsAdapter(items) { item ->
            val args = Bundle().apply {
                putParcelable(MovieDetailsFragment.ARG_MOVIE, item)

            }
            liveBundle.value = args
        }
        liveAdapter.value = cardsAdapter
    }

}

