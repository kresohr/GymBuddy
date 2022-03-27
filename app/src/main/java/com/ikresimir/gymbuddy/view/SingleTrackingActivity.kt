package com.ikresimir.gymbuddy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.ikresimir.gymbuddy.R
import java.text.SimpleDateFormat
import java.util.*

private lateinit var txtTodaysDate : TextView
private lateinit var txtTodaysWeight : TextView
private lateinit var txtTodaysCalories : TextView
class SingleTrackingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracking)

        txtTodaysDate = findViewById(R.id.txtTodaysDate)
        txtTodaysWeight = findViewById(R.id.txtTodaysWeight)
        txtTodaysCalories = findViewById(R.id.txtTodaysCalories)

        val calendar = Calendar.getInstance()
        setStartDate(calendar)
    }

    private fun setStartDate(kalendar: Calendar){
        val dateFormat = "yyyy-MM-dd"
        val formatDate = SimpleDateFormat (dateFormat)
        txtTodaysDate.text = formatDate.format(kalendar.time)
    }
}