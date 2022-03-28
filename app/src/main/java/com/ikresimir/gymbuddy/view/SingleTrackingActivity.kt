package com.ikresimir.gymbuddy.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.viewmodel.SingleTrackingViewModel
import java.text.SimpleDateFormat
import java.util.*

class SingleTrackingActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracking)
        if (Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        var singleTrackingViewModel = SingleTrackingViewModel()
        var txtTodaysDate : TextView = findViewById(R.id.txtTodaysDate)
        var txtTodaysWeight: TextView = findViewById(R.id.txtTodaysWeight)
        var txtTodaysCalories: TextView = findViewById(R.id.txtTodaysCalories)
        var btnSaveEntry: Button = findViewById(R.id.btnSaveStats)

        val calendar = Calendar.getInstance()
        setStartDate(calendar, txtTodaysDate)

        btnSaveEntry.setOnClickListener {
            if(txtTodaysCalories.text.isEmpty() && txtTodaysWeight.text.isEmpty()){
                Toast.makeText(this,"Minimum one field must have data",Toast.LENGTH_LONG).show()
            }
            else{
                when{
                    txtTodaysCalories.text.isEmpty() -> txtTodaysCalories.text= 0.toString()
                    txtTodaysWeight.text.isEmpty() -> txtTodaysWeight.text = 0.0.toString()
                }
                singleTrackingViewModel.saveTrackingData(this, txtTodaysDate.text.toString(),txtTodaysWeight.text.toString().toDouble(),txtTodaysCalories.text.toString().toInt())
            }
        }
    }

    private fun setStartDate(calendar: Calendar, todaysDate: TextView){
        val dateFormat = "yyyy-MM-dd"
        val formatDate = SimpleDateFormat (dateFormat)
        todaysDate.text = formatDate.format(calendar.time)
    }


}