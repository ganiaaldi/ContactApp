package com.aldi.contactapp.database
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "user_table")
data class User (
    var nama : String,
    var email : String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}