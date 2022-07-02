package com.ikresimir.gymbuddy.view.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.model.TrainingProfile
import com.ikresimir.gymbuddy.repository.Repository
import com.ikresimir.gymbuddy.view.SingleTrainingActivity
import com.ikresimir.gymbuddy.view.TrainingListActivity
import com.ikresimir.gymbuddy.viewmodel.repository
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class TrainingListAdapter (private val trainingList: MutableList<TrainingProfile>, val context: Context, val activity: TrainingListActivity) : RecyclerView.Adapter<TrainingListAdapter.ItemViewHolder>() {
        class ItemViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
            val lblTrainingNameItem: TextView = itemView.findViewById(R.id.lblTrainingNameItem)
            val lblTrainingDateItem: TextView = itemView.findViewById(R.id.lblTrainingDateItem)
            val chkBoxTrainingDone: CheckBox = itemView.findViewById(R.id.checkBoxTrainingDone)
            val repository = Repository()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val itemView = LayoutInflater.from(parent.context).inflate(R.layout.training_item,parent,false)
            return ItemViewHolder(itemView)
        }

    interface onItemClick {
        fun mClick(v: View?, position: Int)
    }

        @RequiresApi(Build.VERSION_CODES.O)
        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            val currentItem = trainingList[position]
            val serializeObject = Json.encodeToString(currentItem)

            holder.lblTrainingNameItem.text = currentItem.trainingName
            holder.lblTrainingDateItem.text = currentItem.date

            if (currentItem.isDone || holder.chkBoxTrainingDone.isChecked == true){
                holder.chkBoxTrainingDone.isChecked = true
                holder.chkBoxTrainingDone.isEnabled = false
            }
            else{
             holder.chkBoxTrainingDone.setOnClickListener {
                //Spremiti u bazu uz pomoć triggera i funkcije, ako je training_done == true, zapisi datum i ta sranja u novu tablicu
                 repository.markTrainingAsDone(currentItem.trainingId)
                 holder.chkBoxTrainingDone.isEnabled = false
             }
            }

            // Switch for callback
            holder.lblTrainingNameItem.setOnClickListener {
                val intent = Intent(context, SingleTrainingActivity::class.java)
                intent.putExtra("Training", serializeObject)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                context.startActivity(intent)
                activity.finish()
            }



        }

        override fun getItemCount(): Int {
            return trainingList.size
        }

}