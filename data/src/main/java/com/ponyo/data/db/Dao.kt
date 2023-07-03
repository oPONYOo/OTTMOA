package com.ponyo.data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface InfoDao {

    @Query("SELECT * FROM infoList")
    fun getRecords(): List<DB>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertThings(vararg todoList: DB)

    @Insert
    fun insertRecords(todoEntity: DB)

    @Query("INSERT INTO infoList(id,starRate,memoTxt,thumbnail)VALUES(:id,:starRate,:memoTxt,:thumbnail)")
    fun insertThing(id: String, starRate: Int?, memoTxt: String?, thumbnail: String)

    @Delete
    fun deleteRecords(todoEntity: DB)

    @Update
    fun updateRecords(todoEntity: DB)

}