package com.hb.collegeprojectdemo.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hb.collegeprojectdemo.database.configuration.DatabaseConfigs
import com.hb.collegeprojectdemo.database.model.Category


@Dao
interface CategoryDao {
    @Insert
    suspend fun insert(category: Category)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMany(category: List<Category>):List<Long>

    @Update
    suspend fun update(category: Category)

    @Delete
   suspend fun delete(category: Category)

    @Query("SELECT * FROM " + DatabaseConfigs.tbl_category)
   suspend  fun getAllCategories(): List<Category>

    // Add other queries as needed
}