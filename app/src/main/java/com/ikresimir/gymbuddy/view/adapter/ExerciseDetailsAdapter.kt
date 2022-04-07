package com.ikresimir.gymbuddy.view.adapter

import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.model.ExerciseDetails
import com.ikresimir.gymbuddy.model.TrainingProfile
import com.ikresimir.gymbuddy.view.SingleTrainingActivity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ExerciseDetailsAdapter (private val exerciseDetailsList: MutableList<ExerciseDetails>, val context: Context) : RecyclerView.Adapter<ExerciseDetailsAdapter.ItemViewHolder>() {
        class ItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
            val lblRepsItem: TextView = itemView.findViewById(R.id.lblRepsItem)
            val lblWeightItem: TextView = itemView.findViewById(R.id.lblWeightItem)
            val lblSetItem: TextView = itemView.findViewById(R.id.lblSetItem)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.exercise_details_item,parent,false)
            return ItemViewHolder(itemView)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val currentItem = exerciseDetailsList[position]

            holder.lblRepsItem.text = "Reps: ${currentItem.numberOfReps}"
            holder.lblWeightItem.text = "Weight: ${currentItem.weight}"
            holder.lblSetItem.text = "SET: ${position+1}"

        }

        override fun getItemCount(): Int {
            return exerciseDetailsList.size
        }

    }