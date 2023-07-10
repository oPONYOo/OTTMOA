package com.ponyo.domain.usecase

import com.ponyo.domain.LocalInfoRepository
import com.ponyo.domain.entity.LocalInfo

class UpdateLocalInfoUseCase(
    private val localInfoRepository: LocalInfoRepository
) {
    suspend operator fun invoke(localInfo: LocalInfo) = localInfoRepository.updateRecords(localInfo)

}