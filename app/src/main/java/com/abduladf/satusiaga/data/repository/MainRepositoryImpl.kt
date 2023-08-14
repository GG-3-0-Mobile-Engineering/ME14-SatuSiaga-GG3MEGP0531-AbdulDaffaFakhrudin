package com.abduladf.satusiaga.data.repository

import com.abduladf.satusiaga.data.mapper.toModel
import com.abduladf.satusiaga.data.service.PetabencanaService
import com.abduladf.satusiaga.domain.model.DisasterItem
import com.abduladf.satusiaga.domain.repository.MainRepository
import com.abduladf.satusiaga.utils.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val petabencanaService: PetabencanaService
) : MainRepository {
    override suspend fun getDisasterList(
        period: Int?,
        area: String?,
        disasterType: String?
    ): Flow<ApiResult<List<DisasterItem>>> {
        return flow {
            val response = petabencanaService.getReports(period, disasterType, area)
            if (response.isSuccessful) {
                val geometries = response.body()?.result?.objects?.output?.geometries
                if (geometries != null) {
                    emit(ApiResult.Success(geometries.map { it!!.toModel() }))
                } else {
                    emit(ApiResult.Success(emptyList()))
                }
            } else {
                val errorMsg = response.errorBody()?.string()
                emit(ApiResult.Error(errorMsg.orEmpty()))
            }
        }.onStart {
            emit(ApiResult.Loading(arrayListOf()))
        }
    }
}