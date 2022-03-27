package com.ikresimir.gymbuddy.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.viewmodel.CalorieCalculationViewModel
import java.text.SimpleDateFormat
import java.util.*

private lateinit var btnNewGoal: Button
private lateinit var txtHeight: TextView
private lateinit var txtWeight: TextView
private lateinit var txtAge: TextView
private lateinit var endDate: String
private lateinit var userSex: String
private lateinit var radioBtnEasyActivity: RadioButton
private lateinit var radioBtnMidActivity: RadioButton
private lateinit var radioBtnIntensiveActivity: RadioButton
private lateinit var calorieCalculatorViewModel: CalorieCalculationViewModel
class CalorieCalculationActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calorie_calculation)
        if (Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
        btnNewGoal = findViewById(R.id.btnNewGoal)
        txtAge = findViewById(R.id.txtAge)
        txtHeight = findViewById(R.id.txtCalculatorHeight)
        txtWeight = findViewById(R.id.txtCalculatorWeight)
        radioBtnEasyActivity = findViewById(R.id.radioBtnEasyActivity)
        radioBtnMidActivity = findViewById(R.id.radioBtnMidActivity)
        radioBtnIntensiveActivity = findViewById(R.id.radioBtnIntensiveActivity)
        userSex = ""
        var lblLight = findViewById<TextView>(R.id.lblLight)
        var lblNormal = findViewById<TextView>(R.id.lblNormal)
        var lblIntensive = findViewById<TextView>(R.id.lblIntensive)
        var lblLightWeightResult = findViewById<TextView>(R.id.lblLightWeightResult)
        var lblNormalWeightResult = findViewById<TextView>(R.id.lblNormalWeightResult)
        var lblIntensiveWeightResult = findViewById<TextView>(R.id.lblIntensiveWeightResult)


        calorieCalculatorViewModel = CalorieCalculationViewModel()
        endDate = ""

        getUserData(calorieCalculatorViewModel)
        calculateCalories(calorieCalculatorViewModel, lblLight, lblNormal, lblIntensive, lblLightWeightResult, lblNormalWeightResult, lblIntensiveWeightResult)

        val calendar = Calendar.getInstance()
        getDate(calendar)

        btnNewGoal.setOnClickListener {
            calorieCalculatorViewModel.newGoal(this, endDate)
            val intent = Intent(this, GoalsActivity::class.java)
            this.startActivity(intent)

        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getUserData(viewModel: CalorieCalculationViewModel){
        viewModel.getUserProfileData(this)
        txtHeight.text = viewModel.userHeight.toString()
        txtWeight.text = viewModel.userCurrentWeight.toString()
        txtAge.text = viewModel.userAge.toString()
        userSex = viewModel.userSex

        when{
            viewModel.activity_level == 1 -> radioBtnEasyActivity.isChecked = true
            viewModel.activity_level == 2 -> radioBtnMidActivity.isChecked = true
            viewModel.activity_level == 3 -> radioBtnIntensiveActivity.isChecked = true
        }
    }
    private fun calculateCalories(viewModel: CalorieCalculationViewModel,
                                  lblLight: TextView,
                                  lblNormal: TextView,
                                  lblIntensive: TextView,
                                  lightWeightDrop: TextView,
                                  normalWeightDrop: TextView,
                                  intensiveWeightDrop: TextView){
        viewModel.calculateCalories()
        if(viewModel.bulk){
            lblLight.text = "Light weight gain:"
            lblNormal.text = "Normal weight gain:"
            lblIntensive.text = "Extreme weight gain:"
        }
        lightWeightDrop.text = viewModel.lightWeight.toString()+" Kcal"
        normalWeightDrop.text = viewModel.normalWeight.toString()+" Kcal"
        intensiveWeightDrop.text = viewModel.intensiveWeight.toString()+" Kcal"
    }

    private fun getDate(calendar: Calendar){
        val dateFormat = "yyyy-MM-dd"
        val formatDate = SimpleDateFormat (dateFormat)
        endDate = formatDate.format(calendar.time)
    }

}