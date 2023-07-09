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
    override fun getInfoTings(): List<LocalInfo> =
        infoDao.getRecords().toLocalInfoList()

    override fun insertRecords(localInfo: LocalInfo) =
        infoDao.insertRecords(localInfo.toDB())

    override fun deleteRecords(localInfo: LocalInfo) =
        infoDao.deleteRecords(localInfo.toDB())

    override fun updateRecords(localInfo: LocalInfo) {
        TODO("Not yet implemented")
    }
}