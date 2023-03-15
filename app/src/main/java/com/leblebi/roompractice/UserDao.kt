package com.leblebi.roompractice

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {

    @Query("Select * From user_table")
    fun getAll():List<User>

    @Insert
    fun insertUser(user:User)

    @Query("Delete From user_table")
    fun delete()

    @Update
    fun update(user:User)

    @Delete
    fun delete(user: User)


}