package com.hb.collegeprojectdemo.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hb.collegeprojectdemo.database.RoleType
import com.hb.collegeprojectdemo.database.configuration.DatabaseConfigs
import java.io.Serializable

@Entity(

    tableName = DatabaseConfigs.tbl_category
)
data class Category(

    @PrimaryKey
    val id: Long? = null,


    val name: String,



    ) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Category

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

}




