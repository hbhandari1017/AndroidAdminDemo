package com.hb.collegeprojectdemo.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.hb.collegeprojectdemo.database.configuration.DatabaseConfigs
import com.hb.collegeprojectdemo.database.model.Product

@Dao
interface ProductDao {
    @Insert
    fun insert(product: Product)

    @Update
    fun update(product: Product)

    @Delete
    fun delete(product: Product)

    @Query("SELECT * FROM " + DatabaseConfigs.tbl_product)
    fun getAllProducts(): List<Product>

    // Add other queries as needed
}

