package com.ikresimir.gymbuddy.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ikresimir.gymbuddy.model.Exercise
import com.ikresimir.gymbuddy.model.TrainingProfile
import com.ikresimir.gymbuddy.repository.Repository
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class SingleTrainingViewModel: ViewModel() {
    val repository = Repository()


    // Training object should be complete once "saveTraining" is selected as that's triggered with "Done" button.
    fun saveTraining(context: Context, trainingName: String, date: String, exerciseListJson: String){
        repository.saveTraining(context, trainingName, date, exerciseListJson)
    }
    fun addToList(trainingProfile: TrainingProfile, context: Context, exercise: String){
        val exerciseFromJson = Json.decodeFromString<Exercise>(exercise)
        trainingProfile.exerciseList.add(exerciseFromJson)
    }
}