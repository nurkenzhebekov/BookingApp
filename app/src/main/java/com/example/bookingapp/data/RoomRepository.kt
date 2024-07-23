package com.example.bookingapp.data

import androidx.lifecycle.LiveData
import com.example.bookingapp.data.model.Room

class RoomRepository(private val roomDao: RoomDao) {

    fun getRooms(): LiveData<List<Room>> {
        return roomDao.getAllRooms()
    }
}