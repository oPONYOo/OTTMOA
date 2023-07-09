package com.ponyo.domain.usecase

import com.ponyo.domain.LocalInfoRepository
import com.ponyo.domain.entity.LocalInfo

class GetLocalInfoUseCase(
    private val localInfoRepository: LocalInfoRepository
) {
     suspend operator fun invoke(): List<LocalInfo> = localInfoRepository.getInfoTings()

}