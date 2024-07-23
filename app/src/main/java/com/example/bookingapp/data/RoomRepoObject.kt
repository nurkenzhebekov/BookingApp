package com.example.bookingapp.data

import com.example.bookingapp.R
import com.example.bookingapp.data.model.Room

object RoomRepoObject {
    fun getRooms(): List<Room> {
        return listOf(
            Room(1, "Room 1", "Desc for room 1", R.drawable.room1),
            Room(2, "Room 2", "Desc for room 2", R.drawable.room2),
            Room(3, "Room 3", "Desc for room 3", R.drawable.room3),
            Room(4, "Room 4", "Desc for room 4", R.drawable.room4),
            Room(5, "Room 5", "Desc for room 5", R.drawable.room5)
        )
    }
}