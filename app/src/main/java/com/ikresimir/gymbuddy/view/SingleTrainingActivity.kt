package com.ikresimir.gymbuddy.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.model.Exercise
import com.ikresimir.gymbuddy.viewmodel.SingleTrainingViewModel
import java.text.SimpleDateFormat
import java.util.*

class SingleTrainingActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_training)

        var txtTodayDate: TextView = findViewById(R.id.txtCurrentDateSingleTraining)
        var txtTrainingName: TextView = findViewById(R.id.txtSingleTrainingName)
        var recyclerView: RecyclerView = findViewById(R.id.recyclerViewExerciseList)
        var btnAddExercise: Button = findViewById(R.id.btnAddExercise)
        var btnCompleteTrainingEntry: Button = findViewById(R.id.btnCompleteTrainingEntry)

        val calendar = Calendar.getInstance()
        setStartingDate(calendar, txtTodayDate)
        val viewModel = SingleTrainingViewModel(txtTodayDate.text.toString())
        val activityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult(),
            ActivityResultCallback<ActivityResult>(){
                @Override
                fun onActivityResult(result: ActivityResult){
                    if(result.resultCode == 1){
                        val intent = result.data
                        if(intent!=null){
                            var jsonExercise = intent.getStringExtra("Exercise")
                            // TO DO: Add the exercise in Training (list attribute)
                        }
                    }
                }
            })
        btnAddExercise.setOnClickListener {
            val intent = Intent(this,AddingExercisesActivity::class.java)
            activityLauncher.launch(intent)
        }

        btnCompleteTrainingEntry.setOnClickListener {
            viewModel.saveTraining(this, txtTrainingName.text.toString(),txtTodayDate.text.toString())
        }
    }

    private fun setStartingDate(calendar: Calendar, txtTodayDate: TextView){
        val dateFormat = "yyyy-MM-dd"
        val formatDate = SimpleDateFormat (dateFormat)
        txtTodayDate.text = formatDate.format(calendar.time)
    }
}