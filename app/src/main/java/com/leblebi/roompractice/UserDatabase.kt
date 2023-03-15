package com.leblebi.roompractice

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database (entities = arrayOf(User::class), version = 1)
abstract class UserDatabase:RoomDatabase() {
    abstract fun userDao() : UserDao
    companion object{
        private var INSTANCE: UserDatabase? = null

        fun getInstance(context:Context): UserDatabase {
            if(INSTANCE == null){
                INSTANCE =Room.databaseBuilder(context,UserDatabase::class.java, "user_table")
                    .build()
            }
            return INSTANCE!!
        }
    }
}