package com.ikresimir.gymbuddy.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.ikresimir.gymbuddy.repository.Repository

class GoalsViewModel: ViewModel() {
    val repository = Repository()

    @RequiresApi(Build.VERSION_CODES.O)
    fun rememberGoal(context: Context, desiredWeight: Double, startDate: String, intensity: Int){
        repository.saveStartGoal(context, desiredWeight, startDate, intensity)
    }
}