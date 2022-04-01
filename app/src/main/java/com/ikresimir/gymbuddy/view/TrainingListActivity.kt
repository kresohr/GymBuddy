package com.ikresimir.gymbuddy.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.viewmodel.TrainingListViewModel


class TrainingListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_list)

        var viewModel = TrainingListViewModel()
        viewModel.getListOfTrainings(this)
        var recyclerView: RecyclerView = findViewById(R.id.recyclerViewAllTrainings)
        var btnNewTraining: Button = findViewById(R.id.btnNewTraining)

        btnNewTraining.setOnClickListener {
            val intent = Intent(this,SingleTrainingActivity::class.java)
            this.startActivity(intent)
        }
    }
}