package com.ponyo.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ponyo.domain.entity.LocalInfo

@Entity(tableName = "infoList")
data class DB(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "starRate") val starRate: Int? = null,
    @ColumnInfo(name = "memoTxt") val memoTxt: String? = null,
    @ColumnInfo(name = "thumbnail") val thumbnail: String,
)


fun List<DB>.toLocalInfoList(): List<LocalInfo> =
    map {
        it.toLocalInfo()
    }

fun DB.toLocalInfo(): LocalInfo =
    LocalInfo(
        id = id,
        starRate = starRate,
        memoTxt = memoTxt,
        thumbnail = thumbnail
    )

fun LocalInfo.toDB(): DB =
    DB(
        id = id,
        starRate = starRate,
        memoTxt = memoTxt,
        thumbnail = thumbnail
    )
