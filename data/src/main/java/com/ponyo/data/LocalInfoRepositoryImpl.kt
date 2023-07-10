package com.ponyo.data

import com.ponyo.data.db.InfoDao
import com.ponyo.data.db.toDB
import com.ponyo.data.db.toLocalInfoList
import com.ponyo.domain.LocalInfoRepository
import com.ponyo.domain.entity.LocalInfo
import javax.inject.Inject

class LocalInfoRepositoryImpl @Inject constructor(
    private val infoDao: InfoDao
) : LocalInfoRepository {
    override suspend fun getInfoTings(): List<LocalInfo> {
        val response = runCatching {
            infoDao.getRecords()
        }
        return response.getOrThrow().toLocalInfoList()
    }


    override suspend fun insertRecords(localInfo: LocalInfo) {
        infoDao.insertRecords(localInfo.toDB())
    }


    override suspend fun deleteRecords(localInfo: LocalInfo) =
        infoDao.deleteRecords(localInfo.toDB())

    override suspend fun updateRecords(localInfo: LocalInfo) {
        infoDao.updateRecords(localInfo.toDB())
    }
}