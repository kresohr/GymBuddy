package com.ikresimir.gymbuddy.view

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.model.Exercise
import com.ikresimir.gymbuddy.model.TrainingProfile
import com.ikresimir.gymbuddy.view.adapter.SingleTrainingAdapter
import com.ikresimir.gymbuddy.viewmodel.SingleTrainingViewModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.util.*

class SingleTrainingActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_training)

        var txtDate: TextView = findViewById(R.id.txtCurrentDateSingleTraining)
        var txtTrainingName: TextView = findViewById(R.id.txtSingleTrainingName)
        var btnAddExercise: Button = findViewById(R.id.btnAddExercise)
        var btnCompleteTrainingEntry: Button = findViewById(R.id.btnCompleteTrainingEntry)
        var recyclerView: RecyclerView = findViewById(R.id.recyclerViewExerciseList)

        val calendar = Calendar.getInstance()
        setStartingDate(calendar, txtDate)
        var trainingProfile = TrainingProfile(-1,"","",mutableListOf<Exercise>())
        val viewModel = SingleTrainingViewModel(txtDate.text.toString())
        val getObjectFromRecyclerOnClick =
            intent.getSerializableExtra("Training")
        if (getObjectFromRecyclerOnClick != null){
            trainingProfile = Json.decodeFromString<TrainingProfile>(getObjectFromRecyclerOnClick as String)
            checkIfExisting(txtDate,txtTrainingName,trainingProfile, viewModel)
        }


        val activityLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result ->
                if (result.resultCode == 1) {
                    val intent = result.data
                    if (intent != null) {
                        var jsonExercise = intent.getStringExtra("Exercise")
                        if (jsonExercise != null) {
                            viewModel.addToList(jsonExercise)
                        }
                    }
                }
        }

        //RecyclerView Items
        val listOfTrackedItems = viewModel.exerciseList
        val trackingListAdapter = SingleTrainingAdapter(listOfTrackedItems, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = trackingListAdapter

        btnAddExercise.setOnClickListener {
            val intent = Intent(this,AddingExercisesActivity::class.java)
            activityLauncher.launch(intent)
        }

        btnCompleteTrainingEntry.setOnClickListener {
            if(trainingProfile.trainingId==-1){
                viewModel.saveTraining(this, txtTrainingName.text.toString())
            }
            else{
                viewModel.updateTraining(this, txtTrainingName.text.toString(),trainingProfile.trainingId)
            }

        }
    }

    private fun setStartingDate(calendar: Calendar, txtTodayDate: TextView){
        val dateFormat = "yyyy-MM-dd"
        val formatDate = SimpleDateFormat (dateFormat)
        txtTodayDate.text = formatDate.format(calendar.time)
    }

    private fun checkIfExisting(txtDate: TextView, txtTrainingName: TextView,existingTraining: TrainingProfile, viewModel: SingleTrainingViewModel){
        viewModel.checkIfExisting(existingTraining)
        if (viewModel.existingProfile){
            txtDate.text = viewModel.existingDate
            txtTrainingName.text = viewModel.trainingName
        }
    }
}