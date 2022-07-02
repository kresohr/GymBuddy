package com.ikresimir.gymbuddy.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.view.adapter.TrainingListAdapter
import com.ikresimir.gymbuddy.viewmodel.TrainingListViewModel


class TrainingListActivity : AppCompatActivity(), TrainingListAdapter.onItemClick {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training_list)

        val viewModel = TrainingListViewModel()
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewAllTrainings)
        val btnNewTraining: Button = findViewById(R.id.btnNewTraining)

        //RecyclerView Items
        //Can be improved by passing the new item through intent and adding to this list rather than making 2 DB calls
        val listOfTrackedItems = viewModel.getListOfTrainings(this)
        val trackingListAdapter = TrainingListAdapter(listOfTrackedItems, this, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = trackingListAdapter
        val activityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == 2) {
                listOfTrackedItems.clear()
                recyclerView.adapter?.notifyDataSetChanged()
                val updatedList = viewModel.getListOfTrainings(this)
                val newListAdapter = TrainingListAdapter(updatedList,this, this)
                recyclerView.adapter = newListAdapter
                recyclerView.adapter?.notifyDataSetChanged()
                }
        }

        fun markTrainingAsDone(trainingId: Int){
            viewModel.markTrainingAsDone(trainingId)
        }

        btnNewTraining.setOnClickListener {
            val intent = Intent(this,SingleTrainingActivity::class.java)
            activityLauncher.launch(intent)
            finish()
        }
    }

    override fun mClick(v: View?, position: Int) {
        this.finish()
    }


}