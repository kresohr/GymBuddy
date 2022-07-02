package com.ikresimir.gymbuddy.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.model.GoalHistoryProfile

class GoalHistoryAdapter(private val goalsList:  MutableList<GoalHistoryProfile>, val context: Context) : RecyclerView.Adapter<GoalHistoryAdapter.ItemViewHolder>() {
    class ItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {
        val txtGoalStartingDate: TextView = itemView.findViewById(R.id.txtGoalStartingDate)
        val txtGoalEndingDate: TextView = itemView.findViewById(R.id.txtGoalEndingDate)
        val txtDesiredWeightGoalHistory: TextView = itemView.findViewById(R.id.txtDesiredWeightGoalHistory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.goal_item,parent,false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = goalsList[position]
        holder.txtGoalStartingDate.text = currentItem.goalStart
        holder.txtGoalEndingDate.text = currentItem.goalEnd
        holder.txtDesiredWeightGoalHistory.text = currentItem.desiredWeight.toString()
    }

    override fun getItemCount(): Int {
        return goalsList.size
    }

}