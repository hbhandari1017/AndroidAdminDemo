package com.hb.collegeprojectdemo.database

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hb.collegeprojectdemo.database.dao.ProductDao
import com.hb.collegeprojectdemo.database.dao.UserDao
import com.hb.collegeprojectdemo.database.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class PrePopulateData(private val userDao: UserDao) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        // Insert pre-built data here
        val admin = listOf(
            User(userName = "sa@yopmail.com", password = "password", role = RoleType.ADMIN),
        )
        CoroutineScope(Dispatchers.IO).launch {
            userDao.insertAll(admin)

        }
    }
}
