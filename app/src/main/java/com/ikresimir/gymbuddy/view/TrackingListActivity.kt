package com.ikresimir.gymbuddy.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ikresimir.gymbuddy.R
import com.ikresimir.gymbuddy.model.TrackingProfile
import com.ikresimir.gymbuddy.view.adapter.TrackingListAdapter
import com.ikresimir.gymbuddy.viewmodel.TrackingListViewModel

class TrackingListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracking_list)

        val viewModel = TrackingListViewModel()
        val btnNewEntry: Button = findViewById(R.id.btnNewDailyEntry)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewDailyTracking)
        val listOfTrackedItems = viewModel.getTrackingList(this)
        val trackingListAdapter = TrackingListAdapter(listOfTrackedItems, this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = trackingListAdapter

        btnNewEntry.setOnClickListener {
            val intent = Intent(this, SingleTrackingActivity::class.java)
            this.startActivity(intent)
            finish()
        }
    }
}