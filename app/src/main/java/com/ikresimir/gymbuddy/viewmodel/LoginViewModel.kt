package com.ikresimir.gymbuddy.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ikresimir.gymbuddy.repository.Repository

class LoginViewModel: ViewModel() {

    val repository = Repository()
    var correctLogin = false

    fun loginUser(context: Context, username: String, password: String) {
        repository.checkLogin(context, username, password)
        if(repository.loginCombination){
            correctLogin = true
        }
    }

    fun checkIfProfileSet(context: Context): Boolean{
        repository.checkIfProfileSet(context)
        return repository.profileSet
    }

    fun checkIfLogged(context: Context): Boolean{
        repository.getLoggedInUser(context)
        return !repository.currentUser.isEmpty()
    }
}