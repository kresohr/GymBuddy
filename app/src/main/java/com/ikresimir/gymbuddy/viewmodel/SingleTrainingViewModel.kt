package com.ikresimir.gymbuddy.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.ikresimir.gymbuddy.model.Exercise
import com.ikresimir.gymbuddy.model.TrainingProfile
import com.ikresimir.gymbuddy.repository.Repository
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class SingleTrainingViewModel(val date: String): ViewModel() {
    val repository = Repository()
    val exerciseList = mutableListOf<Exercise>()
    var trainingProfile = TrainingProfile(date,"",exerciseList)


    // Training object should be complete once "saveTraining" is selected as that's triggered with "Done" button.
    @RequiresApi(Build.VERSION_CODES.O)
    fun saveTraining(context: Context, trainingName: String, date: String){
        trainingProfile.trainingName = trainingName
        repository.saveTraining(context, trainingProfile)
    }
    fun addToList(context: Context, exercise: String){
        val exerciseFromJson = Json.decodeFromString<Exercise>(exercise)
        trainingProfile.exerciseList.add(exerciseFromJson)
    }
}