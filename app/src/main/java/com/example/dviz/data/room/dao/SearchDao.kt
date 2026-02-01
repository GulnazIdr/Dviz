package com.example.dviz.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface SearchDao {
//    @Upsert
//    suspend fun addSearch(searchEntity: LocalSearchEntity)
//
//    @Query("SELECT * FROM search_table where userId = :userId ")
//    suspend fun getSearch(userId: String): List<LocalSearchEntity>
//
//    @Delete
//    suspend fun deleteSearch(searchEntity: LocalSearchEntity)
//
//    @Query("update search_table set userId = :newUserId where userId = :tempUserId")
//    suspend fun updateSearchUserId(tempUserId: String, newUserId: String)
}