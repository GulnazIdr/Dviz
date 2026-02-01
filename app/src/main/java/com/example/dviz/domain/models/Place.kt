package com.example.dviz.domain.models

data class Place(
    val id: Int,
    val title: String,
    val city: String,
    val images: List<String>,
    val categories: List<Category>,
    val address: String = "",
    val bodyText: String = "",
    val site_url: String = "",
    val age_restriction: String = ""
)

