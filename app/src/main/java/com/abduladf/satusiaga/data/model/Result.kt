package com.abduladf.satusiaga.data.model


import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("arcs")
    val arcs: List<Any>,
    @SerializedName("bbox")
    val bbox: List<Double>,
    @SerializedName("objects")
    val objects: Objects,
    @SerializedName("type")
    val type: String
)