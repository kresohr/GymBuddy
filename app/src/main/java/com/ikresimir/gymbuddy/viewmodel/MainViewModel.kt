package com.ikresimir.gymbuddy.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.ikresimir.gymbuddy.model.UserProfile
import com.ikresimir.gymbuddy.repository.Repository


class MainViewModel: ViewModel() {
    private val repository = Repository()
    private var userData = mutableListOf<UserProfile>()
    var firstName = ""
    var birthDate = ""
    var userHeight = 0.0
    var userCurrentWeight = 0.0
    var sex = ""
    var newUser = true

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveUserData(context: Context, name: String, birthDate: String, height: Double, currentWeight:Double, sex:String){
        repository.saveUser(context,name,birthDate,height,currentWeight,sex)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getUserProfileData(context: Context){
        userData = repository.getUserData(context)
        for (user in userData){
            firstName = user.name
            if (firstName == ""){
                newUser = true
            }
            else{
                newUser = false
                birthDate = user.birthDate
                userHeight = user.userHeight
                userCurrentWeight = user.userCurrentWeight
                sex = user.sex
            }

        }
    }
}