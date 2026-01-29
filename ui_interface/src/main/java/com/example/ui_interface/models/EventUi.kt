package com.example.ui_interface.models

data class EventUi(
    val id: Int,
    val title: String,
    val price: Double,
    val image: String,
    val amount: Int = 0,
    val categoryUi: CategoryUi
)
