package com.example.randomdog.adapter

import android.app.Application
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.example.randomdog.R
import com.example.randomdog.activities.MainActivity


class GlideAdapter {
    companion object{
        fun setImage(view: ImageView, mediaUrl: String?){
            Glide.with(view.context)
                .load(mediaUrl)
                .placeholder(ColorDrawable(ContextCompat.getColor(view.context, R.color.blue)))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(view)
        }
    }
}