package com.example.userprofileregistration.Model

class UserRepository(private val userRepo: UserProfileDao) {

    fun getAllUser(): List<UserProfile> {
        return userRepo.getUser()
    }

    fun insertRepo(user: UserProfile) {
        return userRepo.insert(user)
    }

    fun deleteRepo(user: UserProfile) {
        return userRepo.delete(user)
    }

    fun updateRepo(user: UserProfile) {
        return userRepo.update(user)
    }

}