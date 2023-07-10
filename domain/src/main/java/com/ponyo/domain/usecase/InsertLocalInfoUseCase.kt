package com.ponyo.domain.usecase

import com.ponyo.domain.LocalInfoRepository
import com.ponyo.domain.entity.LocalInfo

class InsertLocalInfoUseCase(
    private val localInfoRepository: LocalInfoRepository
) {
    suspend operator fun invoke(localInfo: LocalInfo) {
        return localInfoRepository.insertRecords(localInfo)
    }
}