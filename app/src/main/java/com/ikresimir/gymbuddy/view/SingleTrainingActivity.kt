package com.ikresimir.gymbuddy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ikresimir.gymbuddy.R

class SingleTrainingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_training)

        var txtTodayDate: TextView = findViewById(R.id.txtCurrentDateSingleTraining)
        var txtTrainingName: TextView = findViewById(R.id.txtSingleTrainingName)
        var recyclerView: RecyclerView = findViewById(R.id.recyclerViewExerciseList)
        var btnAddExercise: Button = findViewById(R.id.btnAddExercise)
        var btnCompleteTrainingEntry: Button = findViewById(R.id.btnCompleteTrainingEntry)

        btnAddExercise.setOnClickListener {  }
        btnCompleteTrainingEntry.setOnClickListener {  }
    }
}