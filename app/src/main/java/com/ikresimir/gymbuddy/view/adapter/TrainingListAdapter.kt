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
import com.ikresimir.gymbuddy.model.TrainingProfile
import com.ikresimir.gymbuddy.view.SingleTrainingActivity
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class TrainingListAdapter (private val trainingList: MutableList<TrainingProfile>, val context: Context) : RecyclerView.Adapter<TrainingListAdapter.ItemViewHolder>() {
        class ItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
            val lblTrainingNameItem: TextView = itemView.findViewById(R.id.lblTrainingNameItem)
            val lblTrainingDateItem: TextView = itemView.findViewById(R.id.lblTrainingDateItem)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.training_item,parent,false)
            return ItemViewHolder(itemView)
        }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val currentItem = trainingList[position]
            val serializeObject = Json.encodeToString(currentItem)

            holder.lblTrainingNameItem.text = currentItem.trainingName
            holder.lblTrainingDateItem.text = currentItem.date

            holder.lblTrainingNameItem.setOnClickListener {
                val intent = Intent(context, SingleTrainingActivity::class.java)
                intent.putExtra("Training", serializeObject)
                context.startActivity(intent)
            }

        }

        override fun getItemCount(): Int {
            return trainingList.size
        }

}