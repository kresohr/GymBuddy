package com.ikresimir.gymbuddy.model

import java.time.LocalDate


data class GoalProfile(var desiredWeight: Double,
                       var activity_level: Int,
                       var goalHasEnded: Boolean
               ) {

}