package com.example.randomdog.activities

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.LruCache
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.randomdog.adapter.GlideAdapter
import com.example.randomdog.api.RandomDogImageService
import com.example.randomdog.api.RetrofitHelper
import com.example.randomdog.cache.cache
import com.example.randomdog.databinding.ActivityRandomDogGeneratorBinding
import com.example.randomdog.models.MainViewModelFactory
import com.example.randomdog.repository.RandomDogImageRepository
import com.example.randomdog.viewmodel.MainViewModel


class RandomDogGeneratorActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel
    private lateinit var binding: ActivityRandomDogGeneratorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRandomDogGeneratorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        init()

        binding.getRandomImageButton.setOnClickListener { mainViewModel.getApiCall() }

    }

    fun init(){
        val randomImageService = RetrofitHelper.getInstance().create(RandomDogImageService::class.java)
        val repository = RandomDogImageRepository(randomImageService)
        mainViewModel = ViewModelProvider(this, MainViewModelFactory(repository))[MainViewModel::class.java]

        mainViewModel.randomImage.observe(this, Observer {
            GlideAdapter.setImage(binding.randomDogImage, it.message)
            LRUCache(it.message)
        })
    }

    fun LRUCache(message:String){

       val lastString =  cache.get("DOG")

        if(lastString != null && !lastString.isEmpty()){

            var toSaveString = "$message,$lastString"

            val toSaveArray: MutableList<String> = toSaveString.split(",") as MutableList<String>

            if(toSaveArray.size == 21){
                toSaveArray.removeAt(20)
            }
            cache.put("DOG", toSaveArray.joinToString())
        }else{
            cache.put("DOG", message)
        }

    }

    override fun onStop() {
        val cachedString = cache.get("DOG")
        if(cachedString != null && !cachedString.isEmpty() ) {
            Log.d("MYRANDOMDOG", cachedString)
            val sharedPreference = getSharedPreferences("DOG", Context.MODE_PRIVATE)
            var editor = sharedPreference.edit()
            editor.putString("MYDOG", cachedString)
            editor.commit()
        }
        super.onStop()
    }
}