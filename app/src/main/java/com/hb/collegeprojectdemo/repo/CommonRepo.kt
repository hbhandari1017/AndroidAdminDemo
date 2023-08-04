package com.hb.collegeprojectdemo.repo

import com.hb.collegeprojectdemo.database.dao.CategoryDao
import com.hb.collegeprojectdemo.database.dao.ProductDao
import com.hb.collegeprojectdemo.database.dao.UserDao
import com.hb.collegeprojectdemo.database.model.User


class CommonRepo constructor(
    private val productDao: ProductDao,
    private val categoryDao: CategoryDao,
    private val userDao: UserDao,
){

    suspend fun getAllUsers() = userDao.getAllUsers()

    suspend fun addAdmin(user: User) = userDao.insert(user)


}
