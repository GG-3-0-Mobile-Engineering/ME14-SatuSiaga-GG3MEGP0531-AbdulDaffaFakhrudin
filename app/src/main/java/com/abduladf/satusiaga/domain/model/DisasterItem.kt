package com.abduladf.satusiaga.domain.model


data class DisasterItem(
    val imageUrl: String?,
    val title: String?,
    val subtitle: String?,
    val coordinates: List<Double?>? = null
)
