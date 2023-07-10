package com.ponyo.domain

import com.ponyo.domain.entity.LocalInfo


interface LocalInfoRepository {
    suspend fun getInfoTings(): List<LocalInfo>

    suspend fun insertRecords(localInfo: LocalInfo)

    suspend fun deleteRecords(localInfo: LocalInfo)

    suspend fun updateRecords(localInfo: LocalInfo)

}