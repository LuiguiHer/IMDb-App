package com.example.imdb.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.imdb.databinding.ItemCardsHorizontalBinding
import com.example.imdb.model.CardsProfile

class CardsAdapter (
    private val cardsList: List<CardsProfile>,
    private val onClick: (CardsProfile) -> Unit
): RecyclerView.Adapter<CardsAdapter.CardViewHolder>() {

    override fun getItemCount(): Int = cardsList.size

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(cardsList[position])
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding = ItemCardsHorizontalBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CardViewHolder(binding)
    }

    inner  class CardViewHolder(private val binding: ItemCardsHorizontalBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(card: CardsProfile) {
            itemView.setOnClickListener {
                onClick(card)
            }
            binding.cardTitle.text = card.title
            binding.cardDescription.text = card.Description
            binding.cardAmount.text = card.amount.toString()
        }
    }
}