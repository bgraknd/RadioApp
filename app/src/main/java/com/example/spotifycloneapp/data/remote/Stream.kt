package com.example.spotifycloneapp.data.remote

data class Stream(

    val id: Int,
    val is_external: Boolean,
    val isfile: Boolean,
    val rate: Int,
    val works: Boolean
)