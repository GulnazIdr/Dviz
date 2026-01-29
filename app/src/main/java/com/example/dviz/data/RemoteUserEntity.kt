package com.example.dviz.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemoteUserEntity(
    @SerialName("sub") val id: String? = null,
    val name: String,
    val phone: String,
    val password: String,
    val email: String
)