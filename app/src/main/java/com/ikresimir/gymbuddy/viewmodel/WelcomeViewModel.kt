package com.ikresimir.gymbuddy.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ikresimir.gymbuddy.repository.Repository

val repository = Repository()

class WelcomeViewModel: ViewModel() {
    fun checkIfLogged(context: Context): Boolean{
        repository.getLoggedInUser(context)
        if(repository.currentUser.isNotEmpty()){
            return true
        }
        return false
    }
    fun checkIfProfileSet(context: Context): Boolean{
        repository.checkIfProfileSet(context)
        if (repository.profileSet){
            return true
        }
            return false
    }
}