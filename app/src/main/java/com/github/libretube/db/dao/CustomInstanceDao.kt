package com.github.libretube.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.github.libretube.db.obj.CustomInstance

@Dao
interface CustomInstanceDao {
    @Query("SELECT * FROM customInstance")
    fun getAll(): List<CustomInstance>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg customInstances: CustomInstance)

    @Delete
    fun delete(customInstance: CustomInstance)

    @Query("DELETE FROM customInstance")
    fun deleteAll()
}
