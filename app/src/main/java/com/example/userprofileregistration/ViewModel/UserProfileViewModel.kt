package com.example.userprofileregistration.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.userprofileregistration.Model.UserProfile
import com.example.userprofileregistration.Model.UserProfileDatabase
import com.example.userprofileregistration.Model.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserProfileViewModel(application: Application) : AndroidViewModel(application) {

    private val userViewModel: UserRepository


    val liveData = MutableLiveData<List<UserProfile>>()

    init {
        val dao = UserProfileDatabase.getdatabse(application).userDao()
        userViewModel = UserRepository(dao)
        loadViewModel()
    }


    fun insertViewModel(user: UserProfile) {
        viewModelScope.launch(Dispatchers.IO) {
            userViewModel.insertRepo(user)
            loadViewModel()
        }
    }


    fun updateViewModel(user: UserProfile) {
        viewModelScope.launch(Dispatchers.IO) {
            userViewModel.updateRepo(user)
            loadViewModel()
        }
    }

    fun deleteViewModel(user: UserProfile) {
        viewModelScope.launch(Dispatchers.IO) {
            userViewModel.deleteRepo(user)
            loadViewModel()
        }
    }

    fun loadViewModel() {
        viewModelScope.launch(Dispatchers.IO) {
            val data = userViewModel.getAllUser()
            withContext(Dispatchers.Main) {
                liveData.value = data

            }
        }


    }

    fun searchViewModel(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val data = userViewModel.searchUserRepo(query)
            withContext(Dispatchers.Main) {
                liveData.value = data
            }
        }
    }


}