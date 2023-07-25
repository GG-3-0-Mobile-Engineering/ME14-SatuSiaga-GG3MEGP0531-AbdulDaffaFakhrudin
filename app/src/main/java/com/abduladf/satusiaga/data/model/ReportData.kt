package com.abduladf.satusiaga.data.model


import com.google.gson.annotations.SerializedName

data class ReportData(
    @SerializedName("accessabilityFailure")
    val accessabilityFailure: Int,
    @SerializedName("condition")
    val condition: Int,
    @SerializedName("report_type")
    val reportType: String,
    @SerializedName("structureFailure")
    val structureFailure: Int
)