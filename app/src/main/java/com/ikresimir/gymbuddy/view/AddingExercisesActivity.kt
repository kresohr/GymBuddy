package com.ikresimir.gymbuddy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ikresimir.gymbuddy.R

class AddingExercisesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_exercises)

        var txtExerciseName: TextView = findViewById(R.id.txtExerciseName)
        var recyclerView: RecyclerView = findViewById(R.id.recyclerViewExerciseDetailsList)
        var txtExerciseReps: TextView = findViewById(R.id.txtExerciseReps)
        var txtExerciseWeight: TextView = findViewById(R.id.txtExerciseWeight)
        var btnAddExerciseToList: Button = findViewById(R.id.btnAddExerciseToList)
        var btnEndExerciseEntry: Button = findViewById(R.id.btnEndExerciseEntry)

    }
}