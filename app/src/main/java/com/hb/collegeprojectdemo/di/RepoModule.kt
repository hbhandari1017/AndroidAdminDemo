package com.hb.collegeprojectdemo.di

import com.hb.collegeprojectdemo.database.dao.CategoryDao
import com.hb.collegeprojectdemo.database.dao.ProductDao
import com.hb.collegeprojectdemo.database.dao.UserDao
import com.hb.collegeprojectdemo.repo.CommonRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Singleton
    @Provides
    fun provideCommonRepo(
         productDao: ProductDao,
          categoryDao: CategoryDao,
          userDao: UserDao,
    ): CommonRepo{
        return CommonRepo(productDao,categoryDao,userDao)
    }



}














