package com.hb.collegeprojectdemo.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.hb.collegeprojectdemo.database.configuration.DatabaseConfigs
import com.hb.collegeprojectdemo.database.model.Category


@Dao
interface CategoryDao {
    @Insert
    fun insert(category: Category)

    @Update
    fun update(category: Category)

    @Delete
    fun delete(category: Category)

    @Query("SELECT * FROM " + DatabaseConfigs.tbl_category)
    fun getAllCategories(): List<Category>

    // Add other queries as needed
}