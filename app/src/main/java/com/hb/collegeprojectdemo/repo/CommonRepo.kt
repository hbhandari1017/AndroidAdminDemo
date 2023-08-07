package com.hb.collegeprojectdemo.repo

import com.hb.collegeprojectdemo.database.dao.CategoryDao
import com.hb.collegeprojectdemo.database.dao.ProductDao
import com.hb.collegeprojectdemo.database.dao.UserDao
import com.hb.collegeprojectdemo.database.model.Category
import com.hb.collegeprojectdemo.database.model.Product
import com.hb.collegeprojectdemo.database.model.User


class CommonRepo constructor(
    private val productDao: ProductDao,
    private val categoryDao: CategoryDao,
    private val userDao: UserDao,
){

    suspend fun getAllUsers() = userDao.getAllUsers()
    suspend fun getAdmin(name:String) = userDao.getAdmin(name)

    suspend fun addAdmin(user: User) = userDao.insert(user)
    suspend fun getAllCategory() = categoryDao.getAllCategories()
    suspend fun getAllProducts(id:Int) = productDao.getAllProducts(id)
    suspend fun addCategory(addThis: List<Category>) : List<Long>{
       return  categoryDao.insertMany(addThis)
    }

    suspend fun addProduct(addThis: List<Product>) : List<Long>{
        return  productDao.insertMany(addThis)
    }


    suspend fun addCategoryFromUser(addThis: Category) : Long{
        return  categoryDao.insert(addThis)
    }




}
