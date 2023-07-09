package com.example.randomdog.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.randomdog.api.RandomDogImageService
import com.example.randomdog.models.RandomDogImageList

class RandomDogImageRepository(private val randomDogImageService: RandomDogImageService) {

    private val randomDogImageLiveData = MutableLiveData<RandomDogImageList>()

    val RandomDogImage: LiveData<RandomDogImageList>

    get() = randomDogImageLiveData

    suspend fun getRandomDogImage(){
        val result = randomDogImageService.getRandomDogImages()
        Log.d("MYRANDOMDOG", "get random fun out")
        if(result?.body() != null){
            Log.d("MYRANDOMDOG", "get random fun in")
            randomDogImageLiveData.postValue(result.body())
        }
    }

}