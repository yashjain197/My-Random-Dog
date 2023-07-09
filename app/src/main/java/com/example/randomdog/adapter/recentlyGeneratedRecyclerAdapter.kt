package com.example.randomdog.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.randomdog.R

class recentlyGeneratedRecyclerAdapter(private val imageList:List<String>):  RecyclerView.Adapter<recentlyGeneratedRecyclerAdapter.ViewHolder>()
{


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recently_dog_images_layout, parent, false)

        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val urlElement = imageList.get(position)

        GlideAdapter.setImage(holder.randomDogImage, urlElement.trim())

    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val randomDogImage: ImageView = itemView.findViewById(R.id.randomGodImage)
    }
}