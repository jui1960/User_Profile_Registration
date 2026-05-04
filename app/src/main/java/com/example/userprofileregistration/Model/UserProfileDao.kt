package com.example.userprofileregistration.Model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserProfileDao {

    @Insert
    fun insert(profile: UserProfile)

    @Update
    fun update(profile: UserProfile)

    @Delete
    fun delete(profile: UserProfile)

    @Query("SELECT * FROM user_profile ORDER BY id DESC")
    fun getUser(): List<UserProfile>


}