package com.ikresimir.gymbuddy.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.ikresimir.gymbuddy.repository.Repository

class SingleTrackingViewModel: ViewModel() {

    val repository = Repository()

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveTrackingData(context: Context, date: String, weight: Double, calories: Int){
        repository.saveTrackingData(context,date,weight,calories)
    }
}