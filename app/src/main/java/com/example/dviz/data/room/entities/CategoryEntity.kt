package com.example.dviz.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "category_table"
)
data class CategoryEntity (
    @PrimaryKey
    val category_name: String
)