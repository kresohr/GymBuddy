package com.ikresimir.gymbuddy.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.viewmodel.CalorieCalculationViewModel
import com.ikresimir.gymbuddy.viewmodel.GoalsViewModel
import java.text.SimpleDateFormat
import java.util.*

private lateinit var btnNewGoal: Button
private lateinit var txtHeight: TextView
private lateinit var txtWeight: TextView
private lateinit var endDate: String
private lateinit var lblLaganiGubitak: TextView
private lateinit var lblNormalanGubitak: TextView
private lateinit var lblIntenzivanGubitak: TextView
private lateinit var calorieCalculatorViewModel: CalorieCalculationViewModel
class CalorieCalculationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calorie_calculation)

        btnNewGoal = findViewById(R.id.btnNewGoal)
        txtHeight = findViewById(R.id.txtCalculatorHeight)
        txtWeight = findViewById(R.id.txtCalculatorWeight)
        lblLaganiGubitak = findViewById(R.id.lblLaganiGubitak)
        lblNormalanGubitak = findViewById(R.id.lblNormalanGubitak)
        lblIntenzivanGubitak = findViewById(R.id.lblIntenzivanGubitak)
        calorieCalculatorViewModel = CalorieCalculationViewModel()
        endDate = ""
        val calendar = Calendar.getInstance()
        setStartingDate(calendar)

        btnNewGoal.setOnClickListener {
            val intent = Intent(this, GoalsActivity::class.java)
            this.startActivity(intent)
        }
    }

    private fun setStartingDate(calendar: Calendar){
        val dateFormat = "yyyy-MM-dd"
        val formatDate = SimpleDateFormat (dateFormat)
        endDate = formatDate.format(calendar.time)
    }


}