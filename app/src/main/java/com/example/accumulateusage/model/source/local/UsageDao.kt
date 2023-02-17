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

    @Query("DELETE FROM usages")
    suspend fun clear()

    @Query("SELECT * FROM usages WHERE id = :key")
    suspend fun get(key: Long): Usage?

    @Query("SELECT * FROM usages")
    suspend fun getAll(): List<Usage>?

    @Insert
    suspend fun insertUsages(usages: List<Usage>)
}