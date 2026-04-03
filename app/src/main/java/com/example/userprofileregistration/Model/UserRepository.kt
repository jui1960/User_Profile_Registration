package com.example.userprofileregistration.Model

import androidx.lifecycle.LiveData
import androidx.room.Query

class UserRepository(private val userRepo: UserProfileDao) {

    suspend fun insertUser(user: UserProfile) {
        return userRepo.insert(user)
    }

    suspend fun deleteUser(user: UserProfile) {
        return userRepo.delete(user)
    }

    suspend fun updateUser(user: UserProfile) {
        return userRepo.update(user)
    }

    suspend fun getAllUser(): LiveData<List<UserProfile>> {
        return userRepo.getUser()
    }


    suspend fun searchUserRepo(query: String): List<UserProfile> {
        return userRepo.searchUser(query)
    }

}