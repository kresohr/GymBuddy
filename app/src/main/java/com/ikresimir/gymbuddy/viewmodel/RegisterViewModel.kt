package com.ikresimir.gymbuddy.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.ikresimir.gymbuddy.repository.Repository

class RegisterViewModel: ViewModel() {
    val repository = Repository()

    @RequiresApi(Build.VERSION_CODES.O)
    fun registerUser(context: Context, username: String, password: String): Boolean {
        repository.registerUser(context, username, password)
        return (repository.successfullyRegistered)
    }
}