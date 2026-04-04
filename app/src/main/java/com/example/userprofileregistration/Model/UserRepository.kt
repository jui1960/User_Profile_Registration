package com.example.userprofileregistration.Model

import androidx.lifecycle.LiveData
import androidx.room.Query

class UserRepository(private val userRepo: UserProfileDao) {


    suspend fun searchUserRepo(query: String): List<UserProfile> {
        return userRepo.searchUser(query)
    }

    suspend fun getAllUser(): List<UserProfile> {
        return userRepo.getUser()
    }

    suspend fun insertRepo(user: UserProfile) {
        return userRepo.insert(user)
    }

    suspend fun deleteRepo(user: UserProfile) {
        return userRepo.delete(user)
    }

    suspend fun updateRepo(user: UserProfile) {
        return userRepo.update(user)
    }

}