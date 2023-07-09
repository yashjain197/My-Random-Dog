package com.example.randomdog.api

import com.example.randomdog.models.RandomDogImageList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface RandomDogImageService {

    @GET("random")
    suspend fun getRandomDogImages(): Response<RandomDogImageList>
}