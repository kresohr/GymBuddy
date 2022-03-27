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
    var age = 0
    var userHeight = 0.0
    var userCurrentWeight = 0.0
    var sex = ""
    var newUser = true

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveUserData(context: Context, name: String, age: Int, height: Double, currentWeight:Double, sex:String){
        repository.saveUser(context,name,age,height,currentWeight,sex)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getUserProfileData(context: Context){
        userData = repository.getUserData(context)
        if (userData.isEmpty()) {
            newUser=true
        }
            else{
                for (user in userData){
                    newUser = false
                    firstName = user.name
                    age = user.age
                    userHeight = user.userHeight
                    userCurrentWeight = user.userCurrentWeight
                    sex = user.sex
            }
        }
    }
}