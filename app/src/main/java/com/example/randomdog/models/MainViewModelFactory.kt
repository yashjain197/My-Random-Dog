package com.example.randomdog.models

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.randomdog.repository.RandomDogImageRepository
import com.example.randomdog.viewmodel.MainViewModel

class MainViewModelFactory(private val repository: RandomDogImageRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        Log.d("MYRANDOMDOG", "in factory")
        return MainViewModel(repository) as T
    }

}