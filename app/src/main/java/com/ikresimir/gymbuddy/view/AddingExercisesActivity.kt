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
import com.ikresimir.gymbuddy.view.adapter.ExerciseDetailsAdapter
import com.ikresimir.gymbuddy.viewmodel.AddingExerciseViewModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class AddingExercisesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_adding_exercises)

        val viewModel = AddingExerciseViewModel()
        val txtExerciseName: TextView = findViewById(R.id.txtExerciseName)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewExerciseDetailsList)
        val txtExerciseReps: TextView = findViewById(R.id.txtExerciseReps)
        val txtExerciseWeight: TextView = findViewById(R.id.txtExerciseWeight)
        val btnAddExerciseToList: Button = findViewById(R.id.btnAddExerciseToList)
        val btnEndExerciseEntry: Button = findViewById(R.id.btnEndExerciseEntry)
        val exerciseProfile: Exercise
        var activityLoadedFromClick = false

        var trackingListAdapter = ExerciseDetailsAdapter(viewModel.list, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = trackingListAdapter

        val getObjectFromRecyclerOnClick =
            intent.getSerializableExtra("ExerciseDetails")
        val getIndex =
            intent.getIntExtra("Index",-1)
        if (getObjectFromRecyclerOnClick != null){
            activityLoadedFromClick = true
            exerciseProfile = Json.decodeFromString(getObjectFromRecyclerOnClick as String)
            trackingListAdapter = ExerciseDetailsAdapter(exerciseProfile.exerciseList,this)
            recyclerView.adapter = trackingListAdapter
            recyclerView.adapter?.notifyDataSetChanged()
            viewModel.list = exerciseProfile.exerciseList
            txtExerciseName.text = exerciseProfile.exerciseName
        }


        btnAddExerciseToList.setOnClickListener {
            if (txtExerciseReps.text.length>4 || txtExerciseWeight.text.length>4){
                Toast.makeText(this,"Maximum length is 4", Toast.LENGTH_SHORT).show()
            }
            else{
                if (txtExerciseReps.text.isEmpty()){
                    Toast.makeText(this,"Field cannot be empty!", Toast.LENGTH_SHORT).show()
                }
                else{
                    if (txtExerciseWeight.text.isEmpty()){
                        txtExerciseWeight.text = "1"
                    }
                    viewModel.addToList(txtExerciseReps.text.toString().toInt(),txtExerciseWeight.text.toString().toDouble())
                    recyclerView.adapter?.notifyDataSetChanged()
                }
            }

        }
        btnEndExerciseEntry.setOnClickListener {

            if (txtExerciseName.text.isNotEmpty()){
                if (txtExerciseName.text.length > 35){
                    Toast.makeText(this, "Name can have maximum 35 chars", Toast.LENGTH_SHORT).show()
                }
                else{
                    if (activityLoadedFromClick){
                        val result = viewModel.exerciseToJson(txtExerciseName.text.toString())
                        val intent = Intent()
                        intent.putExtra("Exercise",result)
                        intent.putExtra("Indexy",getIndex)
                        this.setResult(3,intent)
                        finish()
                    }
                    else{
                        Toast.makeText(this,"TEST",Toast.LENGTH_SHORT).show()
                    }
//                    else{
//                        val result = viewModel.exerciseToJson(txtExerciseName.text.toString())
//                        val intent = Intent()
//                        intent.putExtra("Exercise",result)
//                        this.setResult(1,intent)
//                        finish()
//                    }
                }
            }
            else{
                Toast.makeText(this,"Name cannot be empty",Toast.LENGTH_SHORT).show()
            }
        }

    }
}