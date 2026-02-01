package com.example.dviz.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_table"
)
data class UserEntity(
    @PrimaryKey
    val uid : String,
    val name: String,
    val phone: String,
    val password: String,
    val email: String
)