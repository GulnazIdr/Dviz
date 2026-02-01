package com.example.dviz.data.room.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.ui_interface.models.CategoryUi
import com.example.ui_interface.models.CityUi
import java.time.LocalDateTime

@Entity(
    tableName = "event_table",
    foreignKeys =[
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"]
        ),
        ForeignKey(
            entity = CityEntity::class,
            parentColumns = ["slug"],
            childColumns = ["cityId"]
        ),
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["uid"],
            childColumns = ["user_id"]
        )
    ]
)
data class EventEntity(
    @PrimaryKey
    val id: Int,
    val user_id: String,
    val title: String,
    val price: Double,
    val images: List<String>,
    val cityId: String,
    val dateTime: LocalDateTime,
    val categoryId: String,
    val description: String,
    val ageLimit: Int,
    val address: String
)

//place пн, ср–вс 11:00–23:00
//DateDto(start=1509811200, end=1509912000)