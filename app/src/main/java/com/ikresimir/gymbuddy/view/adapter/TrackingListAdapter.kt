package com.ikresimir.gymbuddy.view.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.model.TrackingProfile
import com.ikresimir.gymbuddy.viewmodel.TrackingListViewModel

class TrackingListAdapter(private val trackingList: MutableList<TrackingProfile>, val context: Context) : RecyclerView.Adapter<TrackingListAdapter.ItemViewHolder>() {
    class ItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val txtWeight: TextView = itemView.findViewById(R.id.txtTrackingItemWeight)
        val txtCalories: TextView = itemView.findViewById(R.id.txtTrackingItemCalories)
        val lblDate: TextView = itemView.findViewById(R.id.lblTrackingItemDate)
        val btnDelete: ImageView = itemView.findViewById(R.id.btnTrackingItemDelete)
        val activityViewHolder = TrackingListViewModel()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tracking_item,parent,false)
        return ItemViewHolder(itemView)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = trackingList[position]
        val userId = currentItem.userId
        holder.txtWeight.text = currentItem.weight.toString()
        holder.txtCalories.text = currentItem.calories.toString()
        holder.lblDate.text = currentItem.date

        holder.btnDelete.setOnClickListener {
            trackingList.removeAt(position)
            holder.activityViewHolder.deleteEntry(holder.lblDate.text.toString(),userId, context)
            notifyDataSetChanged()

        }
    }

    override fun getItemCount(): Int {
        return trackingList.size
    }
}