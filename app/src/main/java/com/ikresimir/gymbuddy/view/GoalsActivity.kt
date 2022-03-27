package com.ikresimir.gymbuddy.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.RadioButton
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.model.GoalProfile
import com.ikresimir.gymbuddy.viewmodel.GoalsViewModel
import java.text.SimpleDateFormat
import java.util.*


private lateinit var txtDesiredWeight : TextView
private lateinit var txtStartDate : TextView
private lateinit var btnSetGoal : Button
private lateinit var radioBtnEasy: RadioButton
private lateinit var radioBtnMedium: RadioButton
private lateinit var radioBtnIntensive: RadioButton
private lateinit var goalsViewModel: GoalsViewModel


class GoalsActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals)

        if (Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        goalsViewModel = GoalsViewModel()
        checkIfGoalExists(goalsViewModel)

        txtDesiredWeight = findViewById(R.id.txtDesiredWeight)
        txtStartDate = findViewById(R.id.txtGoalStartDate)
        btnSetGoal = findViewById(R.id.btnSetGoal)
        radioBtnEasy = findViewById(R.id.radioBtnEasyActivity)
        radioBtnMedium = findViewById(R.id.radioBtnMidActivity)
        radioBtnIntensive = findViewById(R.id.radioBtnIntensiveActivity)

        val calendar = Calendar.getInstance()
        setStartingDate(calendar)

        btnSetGoal.setOnClickListener {
            checkData(goalsViewModel)
            val intent = Intent(this, MenuActivity::class.java)
            this.startActivity(intent)
        }

    }

    private fun setStartingDate(calendar: Calendar){
        val dateFormat = "yyyy-MM-dd"
        val formatDate = SimpleDateFormat (dateFormat)
        txtStartDate.text = formatDate.format(calendar.time)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkData(goalsViewModel: GoalsViewModel){
        if (txtDesiredWeight.text.isNotEmpty()){
            when{
                radioBtnEasy.isChecked -> rememberGoal(goalsViewModel, 1)
                radioBtnMedium.isChecked -> rememberGoal(goalsViewModel, 2)
                radioBtnIntensive.isChecked -> rememberGoal(goalsViewModel, 3)
                else->{
                    Toast.makeText(applicationContext, "Intensity has to be selected!", Toast.LENGTH_LONG).show()
                }
            }
        }
        else{
            Toast.makeText(applicationContext, "Desired weight is missing!", Toast.LENGTH_LONG).show()
        }
    }

    private fun checkIfGoalExists(goalsViewModel: GoalsViewModel){
        if(goalsViewModel.checkIfGoalExists(this)){
            val intent = Intent(this, MenuActivity::class.java)
            this.startActivity(intent)
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun rememberGoal(viewModel: GoalsViewModel, intensity: Int){
        viewModel.rememberGoal(this, txtDesiredWeight.text.toString().toDouble(),
            txtStartDate.text.toString(), intensity)
    }
}