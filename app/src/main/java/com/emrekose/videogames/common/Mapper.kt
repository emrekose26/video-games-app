package com.emrekose.videogames.common

interface Mapper<F, T> {
    fun mapFrom(from: F): T
}