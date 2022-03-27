package com.ikresimir.gymbuddy.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ikresimir.gymbuddy.R

class TrackingListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tracking_list)

        var newEntry: Button = findViewById(R.id.btnNewDailyEntry)
        var editEntry: Button = findViewById(R.id.btnEditDailyEntry)
        var deleteEntry: Button = findViewById(R.id.btnDeleteDailyEntry)

        newEntry.setOnClickListener {
            val intent = Intent(this, SingleTrackingActivity::class.java)
            this.startActivity(intent)

        }
    }
}