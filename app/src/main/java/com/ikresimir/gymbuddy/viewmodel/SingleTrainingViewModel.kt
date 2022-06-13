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
    var exerciseList = mutableListOf<Exercise>()
    var existingProfile = false
    var trainingId = 0
    var existingDate = ""
    var trainingName = ""


    fun checkIfExisting(existingTraining: TrainingProfile){
        if (existingTraining.trainingName != ""){
            existingProfile = true
            exerciseList = existingTraining.exerciseList
            trainingId = existingTraining.trainingId
            existingDate = existingTraining.date
            trainingName = existingTraining.trainingName
        }
    }

    // Training object should be complete once "saveTraining" is selected as that's triggered with "Done" button.
    @RequiresApi(Build.VERSION_CODES.O)
    fun saveTraining(context: Context, trainingName: String){
        repository.saveTraining(context, date, trainingName, exerciseList)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun updateTraining(context: Context, trainingName: String, trainingId: Int){
        repository.updateTraining(context, date, trainingName, exerciseList, trainingId)
    }
    fun addToList(exercise: String){
        val exerciseFromJson = Json.decodeFromString<Exercise>(exercise)
        exerciseList.add(exerciseFromJson)
    }
    fun replaceItem(exercise: String, index: Int){
       // exerciseList.removeAt(index)
        val exerciseFromJson = Json.decodeFromString<Exercise>(exercise)
        //exerciseList.add(exerciseFromJson)
        exerciseList.set(index, exerciseFromJson)
    }
}