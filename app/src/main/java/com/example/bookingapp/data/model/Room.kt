package com.example.bookingapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Room(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val imageResId: Int
)