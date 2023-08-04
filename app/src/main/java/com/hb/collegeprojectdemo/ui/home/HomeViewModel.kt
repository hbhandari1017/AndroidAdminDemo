package com.hb.collegeprojectdemo.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.hb.collegeprojectdemo.database.model.User
import com.hb.collegeprojectdemo.repo.CommonRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class SignInState {
    object IsLoading : SignInState()
    data class Success(val message: String) : SignInState()
    data class Error(val message: String?) : SignInState()
}

@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel  @Inject constructor(private val repo: CommonRepo) : ViewModel() {

    private var _loginState: MutableLiveData<SignInState> = MutableLiveData()
    val loginState: LiveData<SignInState> get() = _loginState//After otp validation login state (success or failure)
    fun signUp(user: User) {
        _loginState.postValue(SignInState.IsLoading)
        viewModelScope.launch {
            val allUsers = repo.getAllUsers()
            Log.e("impor","user is given by  ${user}")
            var isPresent = false
            Log.e("impor","total users is given by ${Gson().toJson(allUsers)}")
            allUsers.forEach{userDb ->
                if(userDb.userName.equals(user.userName, ignoreCase = true) && userDb.password.equals(user.password,ignoreCase = true) && userDb.role == user.role){
                    isPresent = true
                    _loginState.postValue(SignInState.Success("successfully"))
                }

            }
            if(!isPresent)   _loginState.postValue(SignInState.Error("Bad Credential"))



        }


    }

    fun addAdmin(user: User) {
        viewModelScope.launch {
            repo.addAdmin(user)
        }

    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text
}