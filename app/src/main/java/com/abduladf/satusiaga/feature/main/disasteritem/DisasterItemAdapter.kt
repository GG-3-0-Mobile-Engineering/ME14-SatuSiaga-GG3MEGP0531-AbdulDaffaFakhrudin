package com.abduladf.satusiaga.feature.main.disasteritem

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abduladf.satusiaga.R
import com.abduladf.satusiaga.domain.model.DisasterItem

class DisasterItemAdapter() : RecyclerView.Adapter<DisasterItemViewHolder>(){
    private var items = mutableListOf<DisasterItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisasterItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.disaster_item, parent, false)
        return DisasterItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: DisasterItemViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = items.size

    fun updateData(newItemList: List<DisasterItem>) {
        items.clear()
        items.addAll(newItemList)
        notifyDataSetChanged()
    }

}