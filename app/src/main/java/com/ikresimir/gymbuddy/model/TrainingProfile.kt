package com.ikresimir.gymbuddy.model

import kotlinx.serialization.Serializable
import java.time.LocalDate

@Serializable
data class TrainingProfile(
    var trainingId: Int,
    var date: String,
    var trainingName: String,
    var exerciseList: MutableList<Exercise>,
    var isDone: Boolean
) {

}