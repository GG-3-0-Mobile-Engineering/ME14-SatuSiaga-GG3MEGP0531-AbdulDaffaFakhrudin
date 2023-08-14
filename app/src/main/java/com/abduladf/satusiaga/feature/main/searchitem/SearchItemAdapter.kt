package com.abduladf.satusiaga.feature.main.searchitem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abduladf.satusiaga.R

class SearchItemAdapter(private val onClick: (String) -> Unit) : RecyclerView.Adapter<SearchItemViewHolder>() {
    private var items = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.search_item, parent, false)
        return SearchItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchItemViewHolder, position: Int) {
        val itemText = items[position]
        holder.bind(itemText)
        holder.itemView.setOnClickListener {
            onClick(itemText)
        }
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItems: List<String>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}