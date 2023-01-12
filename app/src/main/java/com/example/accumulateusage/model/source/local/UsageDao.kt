package com.example.accumulateusage.model.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsageDao {
    @Insert
    suspend fun insert(usage: Usage)

    @Delete
    suspend fun delete(usage: Usage)

    @Query("DELETE FROM usage_table")
    suspend fun clear()

    @Query("SELECT * FROM user_table WHERE id = :key")
    suspend fun get(key: Long): Usage?
}