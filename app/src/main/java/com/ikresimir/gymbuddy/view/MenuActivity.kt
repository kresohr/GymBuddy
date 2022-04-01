package com.ikresimir.gymbuddy.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import androidx.annotation.RequiresApi
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.viewmodel.MenuViewModel

private lateinit var btnMenuEditProfile: Button
private lateinit var btnMenuTracking: Button
private lateinit var btnMenuTrainingList: Button
private lateinit var btnMenuGoals: Button
private lateinit var btnMenuBMICalculator: Button
private lateinit var menuViewModel: MenuViewModel
class MenuActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        if (Build.VERSION.SDK_INT > 9) {
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }

        btnMenuEditProfile = findViewById(R.id.btnMenuEditProfile)
        btnMenuTracking = findViewById(R.id.btnMenuTracking)
        btnMenuTrainingList = findViewById(R.id.btnMenuTrainingList)
        btnMenuGoals = findViewById(R.id.btnMenuGoals)
        btnMenuBMICalculator = findViewById(R.id.btnMenuBMICalculator)
        var btnMenuLogout = findViewById<Button>(R.id.btnMenuLogout)
        menuViewModel = MenuViewModel()

        btnMenuEditProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            this.startActivity(intent)
        }
        btnMenuTracking.setOnClickListener {
            val intent = Intent(this, TrackingListActivity::class.java)
            this.startActivity(intent)
        }
        btnMenuTrainingList.setOnClickListener {
            val intent = Intent(this, TrainingListActivity::class.java)
            this.startActivity(intent)
        }
        btnMenuGoals.setOnClickListener {
            if (checkIfGoalExists(menuViewModel)){
                val intent = Intent(this, CalorieCalculationActivity::class.java)
                this.startActivity(intent)
            }
            else{
                val intent = Intent(this, GoalsActivity::class.java)
                this.startActivity(intent)
            }

        }
        btnMenuBMICalculator.setOnClickListener {
            val intent = Intent(this, BMIActivity::class.java)
            this.startActivity(intent)
        }
        btnMenuLogout.setOnClickListener {
            logout(menuViewModel)
            val intent = Intent(this, LoginActivity::class.java)
            this.startActivity(intent)
            finish()
        }

    }
    private fun checkIfGoalExists(viewModel: MenuViewModel): Boolean{
        return (viewModel.checkIfGoalExists(this))
    }

    private fun logout (viewModel: MenuViewModel){
        viewModel.logout(this)
    }
}