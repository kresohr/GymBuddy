package com.ikresimir.gymbuddy.model

import kotlinx.serialization.Serializable

@Serializable
data class UserProfile(var name: String,
                       var age: Int,
                       var userHeight: Double,
                       var userCurrentWeight: Double,
                       var sex: String,
                       var bmi: Double) {
}