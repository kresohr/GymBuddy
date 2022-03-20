package com.ikresimir.gymbuddy.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.ikresimir.gymbuddy.model.UserProfile
import com.ikresimir.gymbuddy.repository.Repository

class CalorieCalculationViewModel: ViewModel() {
    val repository = Repository()
    private var userData = mutableListOf<UserProfile>()
    var userHeight = 0.0
    var userCurrentWeight = 0.0


    @RequiresApi(Build.VERSION_CODES.O)
    fun newGoal(context: Context, endDate: String){
        repository.saveEndGoal(context,endDate)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getUserProfileData(context: Context){
        userData = repository.getUserData(context)
        for (user in userData){
                userHeight = user.userHeight
                userCurrentWeight = user.userCurrentWeight
            }

        }
    }
}