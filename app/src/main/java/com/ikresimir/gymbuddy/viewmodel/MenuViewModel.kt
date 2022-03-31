package com.ikresimir.gymbuddy.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
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
    @RequiresApi(Build.VERSION_CODES.O)
    fun test(){
        repository.test()
    }

    fun checkIfLogged(context: Context): Boolean{
        repository.getLoggedInUser(context)
        return !repository.currentUser.isEmpty()
    }
}