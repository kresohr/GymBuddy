package com.ikresimir.gymbuddy.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.viewmodel.BMIViewModel

private lateinit var txtWeightBMI: TextView
private lateinit var txtHeightBMI: TextView
private lateinit var btnSetGoal: Button
private lateinit var lblBMIResult: TextView
private lateinit var lblBMI: TextView

class BMIActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmiactivity)

        txtWeightBMI = findViewById(R.id.txtWeightBMI)
        txtHeightBMI = findViewById(R.id.txtHeightBMI)
        btnSetGoal = findViewById(R.id.btnCalculateBMI)
        lblBMIResult = findViewById(R.id.lblBMIResult)
        lblBMI = findViewById(R.id.lblBMIText)
        var viewModel = BMIViewModel()
        getBmi(viewModel, lblBMIResult)
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
        if (weight.toString()!="" || height.toString()!=""){
            lblBMIResult.text = viewModel.calculateBMI(weight,height)
            lblBMI.text= "Custom BMI:"
        }
        else{
            val toast = Toast.makeText(this,"Fields cannot be empty", Toast.LENGTH_LONG).show()
        }

    }
    @RequiresApi(Build.VERSION_CODES.O)
    private fun getBmi(viewModel: BMIViewModel, lblBMIResult: TextView){
        viewModel.getBmi(this)
        if(viewModel.bmi.toString() != ""){
            lblBMIResult.text = viewModel.bmi.toString()
        }

    }
}