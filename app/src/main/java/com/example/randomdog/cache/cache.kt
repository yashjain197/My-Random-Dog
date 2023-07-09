package com.example.randomdog.cache

import android.util.LruCache

class cache {

    companion object {
        private val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()
        private val cacheSize = maxMemory / 8
        var lru: LruCache<String, String> = LruCache<String, String>(cacheSize)


        fun put(key: String, value: String) {
            lru.put(key, value)
        }

        fun get(key: String): String {
            if (lru.get(key) == null) {
                return ""
            }
            return lru.get(key)
        }
    }
}