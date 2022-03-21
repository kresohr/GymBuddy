package com.ikresimir.gymbuddy.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ikresimir.gymbuddy.repository.Repository

class MenuViewModel: ViewModel() {
    val repository = Repository()

    fun checkIfGoalExists(context: Context): Boolean{
        return repository.checkIfGoalExists(context)
    }
}