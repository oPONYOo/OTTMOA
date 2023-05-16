package com.ponyo.data.mapper

interface BaseMapper<From, To> {

    fun From.mapTo(): To

}