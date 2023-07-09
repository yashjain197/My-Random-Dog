package com.example.randomdog.activities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.randomdog.adapter.recentlyGeneratedRecyclerAdapter
import com.example.randomdog.cache.cache
import com.example.randomdog.databinding.ActivityRecentlyGeneratedDogsBinding
import kotlinx.coroutines.delay
import java.util.*
import kotlin.math.log

class RecentlyGeneratedDogsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecentlyGeneratedDogsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecentlyGeneratedDogsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setRecyclerView()

        binding.clearCacheBtn.setOnClickListener { clearCache() }
    }

    fun setRecyclerView(){
        binding.randomImageRecyclerView.layoutManager = GridLayoutManager(this,2)

        val fromLRUCache:String =  cache.get("DOG")

        if(fromLRUCache != null && !fromLRUCache.isEmpty()) {
            val urlArray: List<String> = fromLRUCache.split(",")
            val adapter = recentlyGeneratedRecyclerAdapter(urlArray)
            Log.d("MYRANDOMDOG", urlArray.size.toString())
            binding.randomImageRecyclerView.adapter = adapter
        }else{
            val emptyList = listOf<String>()
            val adapter = recentlyGeneratedRecyclerAdapter(emptyList)
            binding.randomImageRecyclerView.adapter = adapter

        }
    }

    //Clearing cache from LRU
    fun clearCache(){
        cache.put("DOG", "")
        val sharedPreference = getSharedPreferences("DOG", Context.MODE_PRIVATE)
        var editor = sharedPreference.edit()
        editor.putString("MYDOG", "")
        editor.commit()
        setRecyclerView()
    }

}