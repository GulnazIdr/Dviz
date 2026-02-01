package com.example.dviz.data.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class ListConverter {
    var gson: Gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String): List<String> {
        val listType: Type? = object : TypeToken<List<String>>() {}.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<String>): String? {
        return gson.toJson(someObjects)
    }
}