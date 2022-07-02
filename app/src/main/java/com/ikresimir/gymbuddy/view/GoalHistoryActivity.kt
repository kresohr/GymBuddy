package com.ikresimir.gymbuddy.view

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.model.GoalHistoryProfile
import com.ikresimir.gymbuddy.view.adapter.GoalHistoryAdapter
import com.ikresimir.gymbuddy.viewmodel.GoalsViewModel

class GoalHistoryActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_history)

        val goalsViewModel = GoalsViewModel()
        var goalHistory = goalsViewModel.getAllGoals(this)
        var recyclerView: RecyclerView = findViewById(R.id.recyclerViewGoalHistory)
        val adapter = GoalHistoryAdapter(goalHistory,this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }
}