package com.example.movietraker

data class Item(
    val title: String,
    val genre: String,
    val description: String,
    val rating: Int,
    val isMovie: Boolean,
    var isWatched: Boolean = false
)
