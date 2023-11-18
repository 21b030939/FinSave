package com.example.finsave.demo.db

import androidx.room.TypeConverter
import java.util.Date

class SpendingConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?):Long? {
        return date?.time?.toLong()
    }

}