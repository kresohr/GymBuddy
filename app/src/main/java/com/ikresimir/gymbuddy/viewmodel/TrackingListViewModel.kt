package com.ikresimir.gymbuddy.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.ikresimir.gymbuddy.model.TrackingProfile
import com.ikresimir.gymbuddy.repository.Repository

class TrackingListViewModel: ViewModel() {
    val repository = Repository()

    fun getTrackingList(context: Context): MutableList<TrackingProfile> {
        return repository.getTrackingList(context)
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun deleteEntry(date: String, userId: Int, context: Context){
        repository.deleteFromTrackingList(date, userId, context)
    }
}