package com.example.dviz.data.room.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.ui_interface.models.CategoryUi
import com.example.ui_interface.models.CityUi
import java.time.LocalDateTime

@Entity(
    tableName = "city_table"
)
data class CityEntity(
    @PrimaryKey
    val slug: String,
    val cityName: String
)