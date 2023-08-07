package com.hb.collegeprojectdemo.di


import android.content.Context
import androidx.room.Room
import com.hb.collegeprojectdemo.database.configuration.AppDatabase
import com.hb.collegeprojectdemo.database.configuration.DatabaseConfigs
import com.hb.collegeprojectdemo.database.dao.CategoryDao
import com.hb.collegeprojectdemo.database.dao.ProductDao
import com.hb.collegeprojectdemo.database.dao.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Singleton
    @Provides
    fun provideDb(@ApplicationContext context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                DatabaseConfigs.databaseName
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase): UserDao {
        return db.userDao()
    }
    @Singleton
    @Provides
    fun provideProductDao(db: AppDatabase): ProductDao {
        return db.productDao()
    }
    @Singleton
    @Provides
    fun provideDAO(db: AppDatabase): CategoryDao {
        return db.categoryDao()
    }




}

























