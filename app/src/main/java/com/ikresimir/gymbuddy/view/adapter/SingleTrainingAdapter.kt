package com.ikresimir.gymbuddy.view.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.model.Exercise
import com.ikresimir.gymbuddy.view.AddingExercisesActivity
import com.ikresimir.gymbuddy.viewmodel.SingleTrainingViewModel
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SingleTrainingAdapter(private val exerciseList: MutableList<Exercise>, val context: Context, val activity: ComponentActivity, val viewModel: SingleTrainingViewModel) : RecyclerView.Adapter<SingleTrainingAdapter.ItemViewHolder>() {
        class ItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
            val lblExerciseParentNameItem: TextView = itemView.findViewById(R.id.lblExerciseParentNameItem)
            val lblExerciseParentTotalSets: TextView = itemView.findViewById(R.id.lblExerciseParentTotalSets)
            val lblExerciseParentTotalWeight: TextView = itemView.findViewById(R.id.lblExerciseParentTotalWeight)
        }

    val activityLauncher = activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == 3) {
            val intent = result.data
            if (intent != null) {
                val jsonExercise = intent.getStringExtra("Exercise")
                val getIndex = intent.getIntExtra("Indexy",-1)
                if (jsonExercise != null) {
                    val exerciseFromJson = Json.decodeFromString<Exercise>(jsonExercise)
                    viewModel.replaceItem(jsonExercise,getIndex)
                    this.notifyDataSetChanged()
                    Toast.makeText(context,"Exercise added", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.exercise_item,parent,false)
            return ItemViewHolder(itemView)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val currentItem = exerciseList[position]
            val numberOfSets = currentItem.exerciseList.count().toString()
            var exerciseName = currentItem.exerciseName
            var sumWeight = 0.0
            val serializeObject = Json.encodeToString(currentItem)
            for (item in currentItem.exerciseList){
                sumWeight += item.weight*item.numberOfReps
            }

            var totalWeight = sumWeight.toString()
            holder.lblExerciseParentNameItem.text = "Exercise name: $exerciseName"
            holder.lblExerciseParentTotalSets.text = "Total sets: $numberOfSets"
            holder.lblExerciseParentTotalWeight.text = "Total weight: $totalWeight KG"

            //Handle on item click
            holder.lblExerciseParentNameItem.setOnClickListener {
                val intent = Intent(context,AddingExercisesActivity::class.java)
                intent.putExtra("ExerciseDetails", serializeObject)
                intent.putExtra("Index", position)
                activityLauncher.launch(intent)
            }
        }

        override fun getItemCount(): Int {
            return exerciseList.size
        }
}