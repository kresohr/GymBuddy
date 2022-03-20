package com.ikresimir.gymbuddy.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ikresimir.gymbuddy.R

private lateinit var btnMenuEditProfile: Button
private lateinit var btnMenuTracking: Button
private lateinit var btnMenuTrainingList: Button
private lateinit var btnMenuGoals: Button
private lateinit var btnMenuBMICalculator: Button
class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        btnMenuEditProfile = findViewById(R.id.btnMenuEditProfile)
        btnMenuTracking = findViewById(R.id.btnMenuTracking)
        btnMenuTrainingList = findViewById(R.id.btnMenuTrainingList)
        btnMenuGoals = findViewById(R.id.btnMenuGoals)
        btnMenuBMICalculator = findViewById(R.id.btnMenuBMICalculator)

        btnMenuEditProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            this.startActivity(intent)
        }
        btnMenuTracking.setOnClickListener {
            val intent = Intent(this, TrackingActivity::class.java)
            this.startActivity(intent)
        }
        btnMenuTrainingList.setOnClickListener {
            val intent = Intent(this, TrainingListActivity::class.java)
            this.startActivity(intent)
        }
        btnMenuGoals.setOnClickListener {
            val intent = Intent(this, GoalsActivity::class.java)
            this.startActivity(intent)
        }
        btnMenuBMICalculator.setOnClickListener {
            val intent = Intent(this, BMIActivity::class.java)
            this.startActivity(intent)
        }

    }
}