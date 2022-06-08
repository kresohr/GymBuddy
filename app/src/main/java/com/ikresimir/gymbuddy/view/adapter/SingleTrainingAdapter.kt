package com.ikresimir.gymbuddy.view.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.model.Exercise
import com.ikresimir.gymbuddy.view.AddingExercisesActivity
import com.ikresimir.gymbuddy.view.SingleTrainingActivity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SingleTrainingAdapter(private val adapterOnClick : (Any) -> Unit,private val exerciseList: MutableList<Exercise>, val context: Context) : RecyclerView.Adapter<SingleTrainingAdapter.ItemViewHolder>() {
        class ItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
            val lblExerciseParentNameItem: TextView = itemView.findViewById(R.id.lblExerciseParentNameItem)
            val lblExerciseParentTotalSets: TextView = itemView.findViewById(R.id.lblExerciseParentTotalSets)
            val lblExerciseParentTotalWeight: TextView = itemView.findViewById(R.id.lblExerciseParentTotalWeight)
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


            // Switch for callback
            holder.lblExerciseParentNameItem.setOnClickListener {
                adapterOnClick(position)
                /*val intent = Intent(context, AddingExercisesActivity::class.java)
                intent.putExtra("ExerciseDetails", serializeObject)
                context.startActivity(intent)*/
            }


        }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?){
            adapterOnClick(adapterPosition)
        }
    }

        override fun getItemCount(): Int {
            return exerciseList.size
        }
}