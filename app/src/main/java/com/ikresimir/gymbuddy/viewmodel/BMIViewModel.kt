package com.ikresimir.gymbuddy.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.ikresimir.gymbuddy.model.UserProfile
import com.ikresimir.gymbuddy.repository.Repository
import java.math.RoundingMode
import java.text.DecimalFormat

class BMIViewModel: ViewModel() {
    val repository = Repository()
    var bmi = 0.0
    private var userData = mutableListOf<UserProfile>()

    @RequiresApi(Build.VERSION_CODES.O)
    fun getBmi (context: Context){
        repository.getUserData(context)
        userData = repository.getUserData(context)
        for (user in userData) {
            bmi = user.bmi
        }
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        bmi = df.format(bmi).toDouble()
    }
    fun calculateBMI(userWeight: Double, userHeight: Double): String {
        val BMI = userWeight/((userHeight*userHeight)/10000)
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(BMI)
    }
}