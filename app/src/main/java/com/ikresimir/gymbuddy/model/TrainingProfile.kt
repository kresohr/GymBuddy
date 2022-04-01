package com.ikresimir.gymbuddy.model

import java.time.LocalDate

data class TrainingProfile(
    var date: String,
    var trainingName: String,
    var exerciseList: MutableList<Exercise>
) {

}