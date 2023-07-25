package com.abduladf.satusiaga.data.model


import com.google.gson.annotations.SerializedName

data class Disaster(
    @SerializedName("result")
    val result: Result,
    @SerializedName("statusCode")
    val statusCode: Int
)