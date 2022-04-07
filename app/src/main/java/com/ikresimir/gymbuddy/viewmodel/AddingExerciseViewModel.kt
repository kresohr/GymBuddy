package com.ikresimir.gymbuddy.viewmodel

import androidx.lifecycle.ViewModel
import com.ikresimir.gymbuddy.model.Exercise
import com.ikresimir.gymbuddy.model.ExerciseDetails
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class AddingExerciseViewModel:ViewModel() {
    var list = mutableListOf<ExerciseDetails>()

    fun addToList(reps: Int,weight: Double){
        list.add(ExerciseDetails(reps,weight))
    }

    fun deleteFromList(){

    }

    fun exerciseToJson(exerciseName: String): String{
        val exercise = Exercise(exerciseName,list)
        return Json.encodeToString(exercise)
    }
}