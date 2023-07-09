package com.example.randomdog.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomdog.models.RandomDogImageList
import com.example.randomdog.repository.RandomDogImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: RandomDogImageRepository ): ViewModel() {

        fun getApiCall(){
            viewModelScope.launch(Dispatchers.IO){
                repository.getRandomDogImage()
            }
        }


    val randomImage: LiveData<RandomDogImageList>
    get() = repository.RandomDogImage

}