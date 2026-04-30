package com.example.userprofileregistration.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserProfile::class], version = 1)
abstract class UserProfileDatabase() : RoomDatabase() {

    abstract fun userDao(): UserProfileDao

    companion object {
        private var INSTANCE: UserProfileDatabase? = null

        fun getDatabase(context: Context): UserProfileDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    UserProfileDatabase::class.java,
                    "User_db"
                )
                    .build()
            }
            return INSTANCE!!
        }
    }
}