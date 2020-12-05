package com.mvvm.database

import com.mvvm.model.Users

class UserRepository(private val dao: UserDAO) {
    val userData = dao.getAllSubscribers()

    suspend fun insert(user: Users): Long {
        return dao.insertSubscriber(user)
    }

    suspend fun update(user: Users): Int {
        return dao.updateSubscriber(user)
    }

    suspend fun delete(user: Users): Int {
        return dao.deleteSubscriber(user)
    }

    suspend fun deleteAll(): Int {
        return dao.deleteAll()
    }
}