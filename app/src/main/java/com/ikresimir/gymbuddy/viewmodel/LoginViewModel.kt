package com.ikresimir.gymbuddy.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ikresimir.gymbuddy.repository.Repository

class LoginViewModel: ViewModel() {

    val repository = Repository()

    fun loginUser(context: Context, username: String, password: String): Boolean {
        repository.checkLogin(context, username, password)
        return repository.loginCombination
    }

    fun checkIfProfileSet(context: Context): Boolean{
        repository.checkIfProfileSet(context)
        return repository.profileSet
    }

}