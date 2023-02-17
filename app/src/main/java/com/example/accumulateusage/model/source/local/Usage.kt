package com.example.accumulateusage.model.source.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "usages")
data class Usage(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "app_name")
    val name: String = "no_name",

    @ColumnInfo(name = "date")
    val date: Date,

    @ColumnInfo(name = "time")
    val time: Long = 0L
)
