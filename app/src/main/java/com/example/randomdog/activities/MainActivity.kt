package com.example.randomdog.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.randomdog.cache.cache
import com.example.randomdog.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.generateRandomDog.setOnClickListener { startRandomDogGeneratorActivity() }

        binding.getRandomDog.setOnClickListener{ startRecentlyDogActivity() }

        getCachedMemory()

    }

    //Starting the Random Dog Image Generator Activity
    fun startRandomDogGeneratorActivity(){
        startActivity(Intent(this, RandomDogGeneratorActivity::class.java))
    }

    //Starting recent dog image activity
    fun startRecentlyDogActivity(){
        startActivity(Intent(this, RecentlyGeneratedDogsActivity::class.java))
    }

    override fun onStop() {
        val sharedPreference =  getSharedPreferences("DOG", Context.MODE_PRIVATE)
        val cachedString = sharedPreference.getString("MYDOG", null)
        if(cachedString != null && !cachedString.isEmpty()){
            cache.put("DOG", cachedString)
        }
        super.onStop()
    }

    fun getCachedMemory(){
        val sharedPreference =  getSharedPreferences("DOG", Context.MODE_PRIVATE)
        val cachedString = sharedPreference.getString("MYDOG", null)
        if(cachedString != null && !cachedString.isEmpty()){
            cache.put("DOG", cachedString)
        }
    }
}