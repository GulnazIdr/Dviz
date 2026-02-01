package com.example.dviz.data.user

import kotlinx.serialization.Serializable

@Serializable
data class UserSerial(
    val name: String = "",
    val password: String = ""
)
