package com.example.bookingapp.data

import android.app.Application

class BookingApp : Application() {
    override fun onCreate() {
        super.onCreate()
        RoomDataInitializer.populateDatabase(this)
    }
}