package com.ponyo.domain

import com.ponyo.domain.entity.LocalInfo


interface LocalInfoRepository {
    val infoThings: List<LocalInfo>

    fun insertRecords(localInfo: LocalInfo)

    fun deleteRecords(localInfo: LocalInfo)

    fun updateRecords(localInfo: LocalInfo)

}