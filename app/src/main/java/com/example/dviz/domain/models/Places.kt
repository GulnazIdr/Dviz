package com.example.dviz.domain.models

data class Places(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Place>
)
