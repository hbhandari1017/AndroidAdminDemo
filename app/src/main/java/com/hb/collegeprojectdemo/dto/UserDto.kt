package com.hb.collegeprojectdemo.dto

import androidx.room.PrimaryKey
import com.hb.collegeprojectdemo.database.RoleType
import java.io.Serializable


data class UserDto(


    val userName: String,

    val password: String,

    val role: RoleType,

    )
