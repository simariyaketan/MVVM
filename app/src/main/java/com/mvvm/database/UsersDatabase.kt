package com.mvvm.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mvvm.model.Users

@Database(entities = [Users::class],version = 1)
abstract class UsersDatabase : RoomDatabase() {

    abstract val subscriberDAO : UserDAO

    companion object{
        @Volatile
        private var INSTANCE : UsersDatabase? = null
        fun getInstance(context: Context):UsersDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        UsersDatabase::class.java,
                        "user_data_table"
                    ).build()
                }
                return instance
            }
        }

    }
}