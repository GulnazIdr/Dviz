package com.example.dviz.domain.models

data class User(
    val name: String = "",
    val phone: String = "",
    val password: String,
    val email: String,
    val id : String? = null
)