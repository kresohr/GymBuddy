package com.ikresimir.gymbuddy.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.viewmodel.AddingExerciseViewModel

class AddingExercisesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_exercises)

        val viewModel = AddingExerciseViewModel()
        var txtExerciseName: TextView = findViewById(R.id.txtExerciseName)
        var recyclerView: RecyclerView = findViewById(R.id.recyclerViewExerciseDetailsList)
        var txtExerciseReps: TextView = findViewById(R.id.txtExerciseReps)
        var txtExerciseWeight: TextView = findViewById(R.id.txtExerciseWeight)
        var btnAddExerciseToList: Button = findViewById(R.id.btnAddExerciseToList)
        var btnEndExerciseEntry: Button = findViewById(R.id.btnEndExerciseEntry)

        btnAddExerciseToList.setOnClickListener {
            viewModel.addToList(txtExerciseReps.text.toString().toInt(),txtExerciseWeight.text.toString().toDouble())
        }
        btnEndExerciseEntry.setOnClickListener {
            var result = viewModel.exerciseToJson(txtExerciseName.text.toString())
            val intent = Intent()
            intent.putExtra("Exercise",result)
            setResult(1,intent)
            finish()
        }

    }
}