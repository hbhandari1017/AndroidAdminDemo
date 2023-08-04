package com.hb.collegeprojectdemo.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hb.collegeprojectdemo.database.RoleType
import com.hb.collegeprojectdemo.database.configuration.DatabaseConfigs
import java.io.Serializable

@Entity(

    tableName = DatabaseConfigs.tbl_user
)
data class User(

    @PrimaryKey
    val id: String,

    val userName: String,

    val password: String,

    val role: RoleType,

    ) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (userName != other.userName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + userName.hashCode()
        result = 31 * result + role.hashCode()
        return result
    }

}




