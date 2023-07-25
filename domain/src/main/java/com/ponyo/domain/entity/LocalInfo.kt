package com.ponyo.domain.entity


data class LocalInfo(
    val id: String,
    val starRate: Int = 0,
    val memoTxt: String? = null,
    val thumbnail: String,
) {
    companion object {
        val Uninitialized = LocalInfo(
            id = "",
            starRate = 1,
            memoTxt = "",
            thumbnail = ""
        )
    }
}


