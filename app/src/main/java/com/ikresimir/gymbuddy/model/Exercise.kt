package com.ikresimir.gymbuddy.model

import kotlinx.serialization.Serializable

@Serializable
data class Exercise(
    var exerciseName: String,
    var exerciseList: MutableList<ExerciseDetails>
) {

}