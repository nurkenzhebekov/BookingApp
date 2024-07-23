package com.example.bookingapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Booking(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val roomId: Int,
    val date: Long
)