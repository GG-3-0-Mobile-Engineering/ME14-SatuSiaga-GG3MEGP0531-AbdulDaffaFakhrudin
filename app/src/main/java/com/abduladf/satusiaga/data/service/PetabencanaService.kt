package com.abduladf.satusiaga.data.service

import com.abduladf.satusiaga.data.api.PetabencanaConstants.PB_REPORTS
import com.abduladf.satusiaga.data.model.DisasterReportsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PetabencanaService {

    @GET(PB_REPORTS)
    suspend fun getReports(
        @Query("timeperiod") period: Int? = 604800,
        @Query("disaster") disasterType: String? = null,
        @Query("admin") area: String? = null
    ): Response<DisasterReportsResponse>

}