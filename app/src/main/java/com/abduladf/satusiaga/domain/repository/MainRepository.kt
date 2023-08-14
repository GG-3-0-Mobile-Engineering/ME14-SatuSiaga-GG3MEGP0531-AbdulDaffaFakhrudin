package com.abduladf.satusiaga.domain.repository

import com.abduladf.satusiaga.domain.model.DisasterItem
import com.abduladf.satusiaga.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun getDisasterList(
        period: Int? = 604800,
        area: String? = null,
        disasterType: String? = null
    ): Flow<ApiResult<List<DisasterItem>>>

}