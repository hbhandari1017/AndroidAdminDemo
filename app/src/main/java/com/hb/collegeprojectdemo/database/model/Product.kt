package com.hb.collegeprojectdemo.database.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.hb.collegeprojectdemo.database.configuration.DatabaseConfigs
import java.io.Serializable



@Entity(
    tableName = DatabaseConfigs.tbl_product,
    foreignKeys = [ForeignKey(entity = Category::class, parentColumns = ["id"], childColumns = ["categoryId"])],
    indices = [Index(value = ["categoryId"])]
)
data class Product(

    @PrimaryKey
    val id: Long? = null,


    val name: String,

    val price: String,
    val categoryId: Int // Foreign key referencing Category table

    ) : Serializable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Product

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + name.hashCode()
        return result
    }

}




