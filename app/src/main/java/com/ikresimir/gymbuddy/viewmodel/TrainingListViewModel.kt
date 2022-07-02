package com.ikresimir.gymbuddy.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import com.ikresimir.gymbuddy.model.TrainingProfile
import com.ikresimir.gymbuddy.repository.Repository

class TrainingListViewModel: ViewModel() {

    val repository = Repository()
    fun getListOfTrainings(context: Context): ArrayList<TrainingProfile>{
        return repository.getTrainingList(context)
    }

    fun markTrainingAsDone(trainingId: Int){
        repository.markTrainingAsDone(trainingId)
    }
}