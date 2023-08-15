package com.abduladf.satusiaga.feature.main.disasteritem

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.abduladf.satusiaga.R
import com.abduladf.satusiaga.databinding.DisasterItemBinding
import com.abduladf.satusiaga.domain.model.DisasterItem
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

class DisasterItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val binding = DisasterItemBinding.bind(itemView)

    fun bind(item: DisasterItem) {
        Glide.with(itemView.context)
            .load(item.imageUrl)
            .placeholder(R.drawable.default_disaster_image)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.disasterItemImageview)
        binding.disasterItemTitle.text = item.title
        binding.disasterItemSubtitle.text = item.subtitle
        binding.disasterItemDate.text = item.date
    }

}