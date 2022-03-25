package com.ikresimir.gymbuddy.viewmodel

import androidx.lifecycle.ViewModel
import java.math.RoundingMode
import java.text.DecimalFormat

class BMIViewModel: ViewModel() {

    fun calculateBMI(userWeight: Double, userHeight: Double): String {
        val BMI = userWeight/((userHeight*userHeight)/10000)
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        return df.format(BMI)
    }
}