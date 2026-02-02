package com.example.dviz.domain.models

data class Place(
    val id: Int,
    val title: String,
    val city: String,
    val images: List<String>,
    val category: Category,
    val address: String = "",
    val bodyText: String = "",
    val price: String,
    val age_restriction: String = ""
)

