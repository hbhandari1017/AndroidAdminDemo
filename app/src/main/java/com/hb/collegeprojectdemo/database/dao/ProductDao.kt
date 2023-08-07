package com.hb.collegeprojectdemo.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hb.collegeprojectdemo.database.configuration.DatabaseConfigs
import com.hb.collegeprojectdemo.database.model.Product

@Dao
interface ProductDao {
    @Insert
    suspend fun insert(product: Product)

    @Update
    suspend fun update(product: Product)

    @Delete
    suspend fun delete(product: Product)

    @Query("SELECT * FROM " + DatabaseConfigs.tbl_product +  " WHERE categoryId = :categoryId")
    suspend fun getAllProducts(categoryId:Int): List<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMany(addThis: List<Product>): List<Long>

    // Add other queries as needed
}

