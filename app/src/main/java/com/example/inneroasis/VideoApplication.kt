package com.example.inneroasis

import android.app.Application

class VideoApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}