package com.ikresimir.gymbuddy.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.ikresimir.gymbuddy.model.GoalProfile
import com.ikresimir.gymbuddy.repository.Repository

class GoalsViewModel: ViewModel() {
    val repository = Repository()
    var goalData = mutableListOf<GoalProfile>()
    var activity_level = 0
    var desiredWeight = 0.0
    var goalHasEnded = false
    @RequiresApi(Build.VERSION_CODES.O)
    fun rememberGoal(context: Context, desiredWeight: Double, startDate: String, intensity: Int){
        repository.saveStartGoal(context, desiredWeight, startDate, intensity)
    }

    fun checkIfGoalExists(context: Context): Boolean{
        return repository.checkIfGoalExists(context)
    }
}