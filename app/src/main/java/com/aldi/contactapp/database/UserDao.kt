package com.aldi.contactapp.database

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.aldi.contactapp.database.User

@Dao
interface UserDao {

    @Insert
    fun insert (user: User)


    @Query("DELETE FROM user_table WHERE nama = 'nama'")
    fun deleteUser()

    @Query("SELECT * FROM user_table ")
    fun getAllUser(): LiveData<List<User>>

}
