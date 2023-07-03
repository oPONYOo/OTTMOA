package com.ponyo.domain.usecase

import com.ponyo.domain.LocalInfoRepository
import com.ponyo.domain.entity.LocalInfo

class GetLocalInfoUseCase(
    private val localInfoRepository: LocalInfoRepository
) {

    val localInfoList: List<LocalInfo> = localInfoRepository.infoThings

}