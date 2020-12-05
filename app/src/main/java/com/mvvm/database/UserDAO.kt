package com.mvvm.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.mvvm.model.Users

@Dao
interface UserDAO {
    @Insert
    suspend fun insertSubscriber(subscriber: Users) : Long

    @Update
    suspend fun updateSubscriber(subscriber: Users) : Int

    @Delete
    suspend fun deleteSubscriber(subscriber: Users) : Int

    @Query("DELETE FROM user_data_table")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM user_data_table")
    fun getAllSubscribers(): LiveData<List<Users>>
}