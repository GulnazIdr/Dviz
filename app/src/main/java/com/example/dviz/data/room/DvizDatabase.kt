package com.example.dviz.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.dviz.data.room.dao.EventDao
import com.example.dviz.data.room.dao.UserDao
import com.example.dviz.data.room.entities.CategoryEntity
import com.example.dviz.data.room.entities.CityEntity
import com.example.dviz.data.room.entities.EventEntity
import com.example.dviz.data.room.entities.UserEntity

@Database(
    entities = [
        CategoryEntity::class,
        CityEntity::class,
        EventEntity::class,
        UserEntity::class
    ],
    version = 1,
    exportSchema = true
)

@TypeConverters(TimeConverter::class, ListConverter::class)
abstract class DvizDatabase: RoomDatabase() {

    abstract fun getEventDao(): EventDao
    abstract fun getUserDao(): UserDao

    companion object{
        @Volatile
        private var INSTANCE: DvizDatabase ?= null

        fun getDatabase(context: Context) : DvizDatabase{
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context.applicationContext, DvizDatabase::class.java, "dviz.db")
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}