package com.abduladf.satusiaga.data.model


import com.google.gson.annotations.SerializedName

data class DisasterReportsResponse(
    @SerializedName("result")
    val result: Result? = null,
    @SerializedName("statusCode")
    val statusCode: Int? = null
) {
    data class Result(
        @SerializedName("arcs")
        val arcs: List<Any?>? = null,
        @SerializedName("bbox")
        val bbox: List<Double?>? = null,
        @SerializedName("objects")
        val objects: Objects? = null,
        @SerializedName("type")
        val type: String? = null
    ) {
        data class Objects(
            @SerializedName("output")
            val output: Output? = null
        ) {
            data class Output(
                @SerializedName("geometries")
                val geometries: List<Geometry?>? = null,
                @SerializedName("type")
                val type: String? = null
            ) {
                data class Geometry(
                    @SerializedName("coordinates")
                    val coordinates: List<Double?>? = null,
                    @SerializedName("properties")
                    val properties: Properties? = null,
                    @SerializedName("type")
                    val type: String? = null
                ) {
                    data class Properties(
                        @SerializedName("created_at")
                        val createdAt: String? = null,
                        @SerializedName("disaster_type")
                        val disasterType: String? = null,
                        @SerializedName("image_url")
                        val imageUrl: String? = null,
                        @SerializedName("partner_code")
                        val partnerCode: Any? = null,
                        @SerializedName("partner_icon")
                        val partnerIcon: Any? = null,
                        @SerializedName("pkey")
                        val pkey: String? = null,
                        @SerializedName("report_data")
                        val reportData: ReportData? = null,
                        @SerializedName("source")
                        val source: String? = null,
                        @SerializedName("status")
                        val status: String? = null,
                        @SerializedName("tags")
                        val tags: Tags? = null,
                        @SerializedName("text")
                        val text: String? = null,
                        @SerializedName("title")
                        val title: String? = null,
                        @SerializedName("url")
                        val url: String? = null
                    ) {
                        data class ReportData(
                            @SerializedName("accessabilityFailure")
                            val accessabilityFailure: Int? = null,
                            @SerializedName("airQuality")
                            val airQuality: Int? = null,
                            @SerializedName("condition")
                            val condition: Int? = null,
                            @SerializedName("evacuationArea")
                            val evacuationArea: Boolean? = null,
                            @SerializedName("evacuationNumber")
                            val evacuationNumber: Int? = null,
                            @SerializedName("fireDistance")
                            val fireDistance: Double? = null,
                            @SerializedName("fireLocation")
                            val fireLocation: FireLocation? = null,
                            @SerializedName("fireRadius")
                            val fireRadius: FireRadius? = null,
                            @SerializedName("flood_depth")
                            val floodDepth: Int? = null,
                            @SerializedName("impact")
                            val impact: Int? = null,
                            @SerializedName("personLocation")
                            val personLocation: PersonLocation? = null,
                            @SerializedName("report_type")
                            val reportType: String? = null,
                            @SerializedName("structureFailure")
                            val structureFailure: Int? = null,
                            @SerializedName("visibility")
                            val visibility: Int? = null,
                            @SerializedName("volcanicSigns")
                            val volcanicSigns: List<Int?>? = null
                        ) {
                            data class FireLocation(
                                @SerializedName("lat")
                                val lat: Double? = null,
                                @SerializedName("lng")
                                val lng: Double? = null
                            )

                            data class FireRadius(
                                @SerializedName("lat")
                                val lat: Double? = null,
                                @SerializedName("lng")
                                val lng: Double? = null
                            )

                            data class PersonLocation(
                                @SerializedName("lat")
                                val lat: Double? = null,
                                @SerializedName("lng")
                                val lng: Double? = null
                            )
                        }

                        data class Tags(
                            @SerializedName("district_id")
                            val districtId: Any? = null,
                            @SerializedName("instance_region_code")
                            val instanceRegionCode: String? = null,
                            @SerializedName("local_area_id")
                            val localAreaId: String? = null,
                            @SerializedName("region_code")
                            val regionCode: String? = null
                        )
                    }
                }
            }
        }
    }
}