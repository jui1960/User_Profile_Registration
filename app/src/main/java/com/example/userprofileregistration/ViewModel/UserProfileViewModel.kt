package com.example.userprofileregistration.ViewModel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.userprofileregistration.Model.UserProfile
import com.example.userprofileregistration.Model.UserProfileDatabase
import com.example.userprofileregistration.Model.UserRepository

class UserProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val userViewModel: UserRepository


    val liveData = MutableLiveData<List<UserProfile>>()

    init {
        val dao = UserProfileDatabase.getDatabase(application).userDao()
        userViewModel = UserRepository(dao)
        loadViewModel()
    }


    fun insertViewModel(user: UserProfile) {

        userViewModel.insertRepo(user)
        loadViewModel()
    }


    fun updateViewModel(user: UserProfile) {

        userViewModel.updateRepo(user)
        loadViewModel()
    }


    fun deleteViewModel(user: UserProfile) {

        userViewModel.deleteRepo(user)
        loadViewModel()
    }


    fun loadViewModel() {
        val data = userViewModel.getAllUser()
        liveData.value = data
    }

}