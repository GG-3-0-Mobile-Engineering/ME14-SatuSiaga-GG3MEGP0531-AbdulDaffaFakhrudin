package com.abduladf.satusiaga.domain.usecase

import com.abduladf.satusiaga.domain.repository.MainRepository
import javax.inject.Inject

class MainUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend fun getDisasterList(
        period: Int?,
        area: String?,
        disasterType: String?
    ) = mainRepository.getDisasterList(period, area, disasterType)

}