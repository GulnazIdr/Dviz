package com.example.dviz.data.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.dviz.data.room.entities.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * from user_table where uid = :userId")
    suspend fun getUser(userId: String): UserEntity?

    @Upsert
    suspend fun setUser(user: UserEntity)

    @Update
    suspend fun updateUser(user: UserEntity)

    @Query("UPDATE user_table set password = :password where uid = :userId")
    suspend fun updatePassword(userId: String,password: String)

    @Query("DELETE FROM user_table where uid = :userId")
    suspend fun deleteUser(userId: String)
}