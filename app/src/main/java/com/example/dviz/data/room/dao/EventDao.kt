package com.example.dviz.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.dviz.data.room.entities.CategoryEntity
import com.example.dviz.data.room.entities.EventEntity

@Dao
interface EventDao {
    @Query("select * from event_table where id = :eventId limit 1")
    suspend fun getEventById(eventId: Int): EventEntity

    @Upsert
    suspend fun setEvents(events: List<EventEntity>)

    @Upsert
    suspend fun setCategories(categories: List<CategoryEntity>)

    @Query("SELECT * FROM event_table where user_id = :uid")
    suspend fun getEventsByUserId(uid: String): List<EventEntity>

    @Query("SELECT * FROM category_table")
    suspend fun getCategories(): List<CategoryEntity>

    @Query("SELECT * FROM category_table where category_name = :id")
    suspend fun getCategoryById(id: String): CategoryEntity

    @Query("SELECT * FROM event_table WHERE categoryId = :categoryId and user_id = :uid")
    suspend fun getEventsByCategoryUid(categoryId: Int, uid: String): List<EventEntity>
}