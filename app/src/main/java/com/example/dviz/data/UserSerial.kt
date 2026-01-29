package com.example.dviz.data

import kotlinx.serialization.Serializable

@Serializable
data class UserSerial(
    val name: String = "",
    val password: String = ""
)
