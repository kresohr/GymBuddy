package com.ikresimir.gymbuddy.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
private lateinit var endDate: String
private lateinit var radioBtnEasyActivity: RadioButton
private lateinit var radioBtnMidActivity: RadioButton
private lateinit var radioBtnIntensiveActivity: RadioButton
private lateinit var calorieCalculatorViewModel: CalorieCalculationViewModel
class CalorieCalculationActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calorie_calculation)
        btnNewGoal = findViewById(R.id.btnNewGoal)
        txtHeight = findViewById(R.id.txtCalculatorHeight)
        txtWeight = findViewById(R.id.txtCalculatorWeight)
        radioBtnEasyActivity = findViewById(R.id.radioBtnEasyActivity)
        radioBtnMidActivity = findViewById(R.id.radioBtnMidActivity)
        radioBtnIntensiveActivity = findViewById(R.id.radioBtnIntensiveActivity)
        calorieCalculatorViewModel = CalorieCalculationViewModel()
        endDate = ""

        getUserData(calorieCalculatorViewModel)

        val calendar = Calendar.getInstance()
        getDate(calendar)

        btnNewGoal.setOnClickListener {
            val intent = Intent(this, GoalsActivity::class.java)
            this.startActivity(intent)
            calorieCalculatorViewModel.newGoal(this, endDate)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getUserData(viewModel: CalorieCalculationViewModel){
        viewModel.getUserProfileData(this)
        txtHeight.text = viewModel.userHeight.toString()
        txtWeight.text = viewModel.userCurrentWeight.toString()
        when{
            viewModel.activity_level == 1 -> radioBtnEasyActivity.isChecked = true
            viewModel.activity_level == 2 -> radioBtnMidActivity.isChecked = true
            viewModel.activity_level == 3 -> radioBtnIntensiveActivity.isChecked = true
        }
    }

    private fun getDate(calendar: Calendar){
        val dateFormat = "yyyy-MM-dd"
        val formatDate = SimpleDateFormat (dateFormat)
        endDate = formatDate.format(calendar.time)
    }


}