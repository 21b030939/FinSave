package com.example.finsave.demo.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "spending_table")
class Spending(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "purchaseDate") val purchaseDate: Date,
    @ColumnInfo(name = "value") val value: Int
)