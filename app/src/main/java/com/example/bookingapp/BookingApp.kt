package com.example.bookingapp

import android.app.Application
import com.example.bookingapp.data.RoomDataInitializer

class BookingApp : Application() {
    override fun onCreate() {
        super.onCreate()
        RoomDataInitializer.populateDatabase(this)
    }
}