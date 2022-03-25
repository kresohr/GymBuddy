package com.ikresimir.gymbuddy.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.ikresimir.gymbuddy.model.GoalProfile
import com.ikresimir.gymbuddy.model.UserProfile
import com.ikresimir.gymbuddy.repository.Repository

class CalorieCalculationViewModel: ViewModel() {
    val repository = Repository()
    private var userData = mutableListOf<UserProfile>()
    private var goalData = mutableListOf<GoalProfile>()
    var userHeight = 0.0
    var userCurrentWeight = 0.0
    var userAge = 0
    var activity_level = 0
    var desiredWeight= 0.0
    var userSex = ""
    var lightWeight = 0
    var normalWeight = 0
    var intensiveWeight = 0
    var bulk = false


    @RequiresApi(Build.VERSION_CODES.O)
    fun newGoal(context: Context, endDate: String){
        repository.saveEndGoal(context,endDate)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getUserProfileData(context: Context){
        userData = repository.getUserData(context)
        for (user in userData){
            userHeight = user.userHeight
            userCurrentWeight = user.userCurrentWeight
            userAge = user.age
            userSex = user.sex
        }
        goalData = repository.getGoalInformation(context)
        for (goal in goalData){
            activity_level = goal.activity_level
            desiredWeight = goal.desiredWeight
        }
    }
    fun calculateCalories(){
        /*
        BMR is basal metabolic rate also known as your "daily caloric requirement".
        Depending on your activity, BMR has to be multiplied with that number (1.2, 1.55 or 1.9)
        In order to lose or gain weight +/- 250Kcal per each weight drop/gain level
        */
        if (userSex == "m"){
            /*
           BMR = 10W + 6.25H - 5A + 5
           Easy Activity Level: BMR × 1.2
           Mid Activity Level: BMR × 1.55
           Intensive Activity Level: BMR × 1.9
           */
            if(desiredWeight>userCurrentWeight){
                //User wants to gain muscle
                    bulk = true
                when(activity_level){
                    1 -> {
                        lightWeight = (((10*userCurrentWeight+6.25*userHeight-5*userAge+5)*1.2)+250).toInt()
                        normalWeight = (((10*userCurrentWeight+6.25*userHeight-5*userAge+5)*1.2)+500).toInt()
                        intensiveWeight = (((10*userCurrentWeight+6.25*userHeight-5*userAge+5)*1.2)+1000).toInt()
                    }
                    2->{
                        lightWeight = (((10*userCurrentWeight+6.25*userHeight-5*userAge+5)*1.55)+250).toInt()
                        normalWeight = (((10*userCurrentWeight+6.25*userHeight-5*userAge+5)*1.55)+500).toInt()
                        intensiveWeight = (((10*userCurrentWeight+6.25*userHeight-5*userAge+5)*1.55)+1000).toInt()
                    }
                    3->{
                        lightWeight = (((10*userCurrentWeight+6.25*userHeight-5*userAge+5)*1.9)+250).toInt()
                        normalWeight = (((10*userCurrentWeight+6.25*userHeight-5*userAge+5)*1.9)+500).toInt()
                        intensiveWeight = (((10*userCurrentWeight+6.25*userHeight-5*userAge+5)*1.9)+1000).toInt()
                    }
                }
            }
            if(desiredWeight<userCurrentWeight){
                //User wants to lose fat
                when(activity_level) {
                    1 -> {
                        lightWeight =
                            (((10 * userCurrentWeight + 6.25 * userHeight - 5 * userAge + 5) * 1.2) - 250).toInt()
                        normalWeight =
                            (((10 * userCurrentWeight + 6.25 * userHeight - 5 * userAge + 5) * 1.2) - 500).toInt()
                        intensiveWeight =
                            (((10 * userCurrentWeight + 6.25 * userHeight - 5 * userAge + 5) * 1.2) - 1000).toInt()
                    }
                    2 -> {
                        lightWeight =
                            (((10 * userCurrentWeight + 6.25 * userHeight - 5 * userAge + 5) * 1.55) - 250).toInt()
                        normalWeight =
                            (((10 * userCurrentWeight + 6.25 * userHeight - 5 * userAge + 5) * 1.55) - 500).toInt()
                        intensiveWeight =
                            (((10 * userCurrentWeight + 6.25 * userHeight - 5 * userAge + 5) * 1.55) - 1000).toInt()
                    }
                    3 -> {
                        lightWeight =
                            (((10 * userCurrentWeight + 6.25 * userHeight - 5 * userAge + 5) * 1.9) - 250).toInt()
                        normalWeight =
                            (((10 * userCurrentWeight + 6.25 * userHeight - 5 * userAge + 5) * 1.9) - 500).toInt()
                        intensiveWeight =
                            (((10 * userCurrentWeight + 6.25 * userHeight - 5 * userAge + 5) * 1.9) - 1000).toInt()
                    }
                }
            }
        }
        if (userSex=="f"){
            /*
            BMR = 10W + 6.25H - 5A - 161
            */
            if(desiredWeight>userCurrentWeight){
                //User wants to gain muscle
                    bulk = true
                when(activity_level){
                    1 -> {
                        lightWeight = (((10*userCurrentWeight+6.25*userHeight-5*userAge-161)*1.2)+250).toInt()
                        normalWeight = (((10*userCurrentWeight+6.25*userHeight-5*userAge-161)*1.2)+500).toInt()
                        intensiveWeight = (((10*userCurrentWeight+6.25*userHeight-5*userAge-161)*1.2)+1000).toInt()
                    }
                    2->{
                        lightWeight = (((10*userCurrentWeight+6.25*userHeight-5*userAge-161)*1.55)+250).toInt()
                        normalWeight = (((10*userCurrentWeight+6.25*userHeight-5*userAge-161)*1.55)+500).toInt()
                        intensiveWeight = (((10*userCurrentWeight+6.25*userHeight-5*userAge-161)*1.55)+1000).toInt()
                    }
                    3->{
                        lightWeight = (((10*userCurrentWeight+6.25*userHeight-5*userAge-161)*1.9)+250).toInt()
                        normalWeight = (((10*userCurrentWeight+6.25*userHeight-5*userAge-161)*1.9)+500).toInt()
                        intensiveWeight = (((10*userCurrentWeight+6.25*userHeight-5*userAge-161)*1.9)+1000).toInt()
                    }
                }
            }
            if(desiredWeight<userCurrentWeight){
                //User wants to lose fat
                when(activity_level) {
                    1 -> {
                        lightWeight =
                            (((10 * userCurrentWeight + 6.25 * userHeight - 5 * userAge - 161) * 1.2) - 250).toInt()
                        normalWeight =
                            (((10 * userCurrentWeight + 6.25 * userHeight - 5 * userAge - 161) * 1.2) - 500).toInt()
                        intensiveWeight =
                            (((10 * userCurrentWeight + 6.25 * userHeight - 5 * userAge - 161) * 1.2) - 1000).toInt()
                    }
                    2 -> {
                        lightWeight =
                            (((10 * userCurrentWeight + 6.25 * userHeight - 5 * userAge - 161) * 1.55) - 250).toInt()
                        normalWeight =
                            (((10 * userCurrentWeight + 6.25 * userHeight - 5 * userAge - 161) * 1.55) - 500).toInt()
                        intensiveWeight =
                            (((10 * userCurrentWeight + 6.25 * userHeight - 5 * userAge - 161) * 1.55) - 1000).toInt()
                    }
                    3 -> {
                        lightWeight =
                            (((10 * userCurrentWeight + 6.25 * userHeight - 5 * userAge - 161) * 1.9) - 250).toInt()
                        normalWeight =
                            (((10 * userCurrentWeight + 6.25 * userHeight - 5 * userAge - 161) * 1.9) - 500).toInt()
                        intensiveWeight =
                            (((10 * userCurrentWeight + 6.25 * userHeight - 5 * userAge - 161) * 1.9) - 1000).toInt()
                    }
                }
            }
        }
    }
}
