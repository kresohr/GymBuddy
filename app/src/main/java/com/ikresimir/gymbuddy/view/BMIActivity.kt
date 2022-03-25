package com.ikresimir.gymbuddy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.viewmodel.BMIViewModel

private lateinit var txtWeightBMI: TextView
private lateinit var txtHeightBMI: TextView
private lateinit var btnSetGoal: Button
private lateinit var lblBMIResult: TextView
private lateinit var lblBMI: TextView

class BMIActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmiactivity)

        txtWeightBMI = findViewById(R.id.txtWeightBMI)
        txtHeightBMI = findViewById(R.id.txtHeightBMI)
        btnSetGoal = findViewById(R.id.btnCalculateBMI)
        lblBMIResult = findViewById(R.id.lblBMIResult)
        lblBMI = findViewById(R.id.lblBMIText)
        var viewModel = BMIViewModel()

        btnSetGoal.setOnClickListener {
            if (txtWeightBMI.text.isNotEmpty() && txtHeightBMI.text.isNotEmpty() ) {
                calculateBMI(viewModel, txtWeightBMI.text.toString().toDouble(), txtHeightBMI.text.toString().toDouble())
            }
            else{
                val text = "Height and weight entry cannot be empty or 0"
                val duration = Toast.LENGTH_LONG
                val toast = Toast.makeText(applicationContext, text, duration)
                toast.show()
            }
        }
    }

    private fun calculateBMI(viewModel: BMIViewModel, weight: Double, height: Double){
        lblBMIResult.text = viewModel.calculateBMI(weight,height)
        lblBMI.text= "BMI:"
    }
}