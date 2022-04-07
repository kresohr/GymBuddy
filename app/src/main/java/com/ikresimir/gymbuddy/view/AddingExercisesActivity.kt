package com.ikresimir.gymbuddy.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.model.Exercise
import com.ikresimir.gymbuddy.model.ExerciseDetails
import com.ikresimir.gymbuddy.view.adapter.ExerciseDetailsAdapter
import com.ikresimir.gymbuddy.view.adapter.TrainingListAdapter
import com.ikresimir.gymbuddy.viewmodel.AddingExerciseViewModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class AddingExercisesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_exercises)

        val viewModel = AddingExerciseViewModel()
        val txtExerciseName: TextView = findViewById(R.id.txtExerciseName)
        var recyclerView: RecyclerView = findViewById(R.id.recyclerViewExerciseDetailsList)
        var txtExerciseReps: TextView = findViewById(R.id.txtExerciseReps)
        var txtExerciseWeight: TextView = findViewById(R.id.txtExerciseWeight)
        var btnAddExerciseToList: Button = findViewById(R.id.btnAddExerciseToList)
        var btnEndExerciseEntry: Button = findViewById(R.id.btnEndExerciseEntry)
        var exerciseProfile: Exercise
        var trackingListAdapter = ExerciseDetailsAdapter(viewModel.list, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = trackingListAdapter

        val getObjectFromRecyclerOnClick =
            intent.getSerializableExtra("ExerciseDetails")
        if (getObjectFromRecyclerOnClick != null){
            exerciseProfile = Json.decodeFromString(getObjectFromRecyclerOnClick as String)
            trackingListAdapter = ExerciseDetailsAdapter(exerciseProfile.exerciseList,this)
            recyclerView.adapter = trackingListAdapter
            recyclerView.adapter?.notifyDataSetChanged()
            viewModel.list = exerciseProfile.exerciseList
            txtExerciseName.text = exerciseProfile.exerciseName
        }


        btnAddExerciseToList.setOnClickListener {
            viewModel.addToList(txtExerciseReps.text.toString().toInt(),txtExerciseWeight.text.toString().toDouble())
            recyclerView.adapter?.notifyDataSetChanged()
        }
        btnEndExerciseEntry.setOnClickListener {
            if (txtExerciseName.text.isNotEmpty()){
                val result = viewModel.exerciseToJson(txtExerciseName.text.toString())
                val intent = Intent()
                intent.putExtra("Exercise",result)
                this.setResult(1,intent)
                this.finish()
            }
            Toast.makeText(this,"Name cannot be empty",Toast.LENGTH_SHORT).show()

        }

    }
}