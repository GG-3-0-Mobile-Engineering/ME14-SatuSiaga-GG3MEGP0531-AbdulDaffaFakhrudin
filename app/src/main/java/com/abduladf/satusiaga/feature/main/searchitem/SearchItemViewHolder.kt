package com.abduladf.satusiaga.feature.main.searchitem

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.abduladf.satusiaga.databinding.SearchItemBinding

class SearchItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    private val binding = SearchItemBinding.bind(itemView)

    fun bind(itemText: String) {
        binding.searchItemText.text = itemText
    }
}