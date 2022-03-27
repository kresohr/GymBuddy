package com.ikresimir.gymbuddy.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ikresimir.gymbuddy.repository.Repository

class MenuViewModel: ViewModel() {
    val repository = Repository()

    fun checkIfGoalExists(context: Context): Boolean{
        return repository.checkIfGoalExists(context)
    }

    fun logout(context: Context){
        repository.logout(context)
    }

    fun checkIfLogged(context: Context): Boolean{
        repository.getLoggedInUser(context)
        return !repository.currentUser.isEmpty()
    }

}