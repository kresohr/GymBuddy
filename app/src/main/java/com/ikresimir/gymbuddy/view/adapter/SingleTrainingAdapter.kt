package com.ikresimir.gymbuddy.view.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.model.Exercise

class SingleTrainingAdapter(private val exerciseList: MutableList<Exercise>, val context: Context) : RecyclerView.Adapter<SingleTrainingAdapter.ItemViewHolder>() {
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
            for (item in currentItem.exerciseList){
                sumWeight += item.weight*item.numberOfReps
            }
            var totalWeight = sumWeight.toString()
            holder.lblExerciseParentNameItem.text = "Exercise name: $exerciseName"
            holder.lblExerciseParentTotalSets.text = "Total sets: $numberOfSets"
            holder.lblExerciseParentTotalWeight.text = "Total weight: $totalWeight KG"
        }

        override fun getItemCount(): Int {
            return exerciseList.size
        }
}