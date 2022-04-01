package com.ikresimir.gymbuddy.model

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseDetails(
    var setNumber: Int,
    var numberOfReps: Int,
    var weight: Double
) {

}