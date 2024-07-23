package com.example.bookingapp.data

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object RoomDataInitializer {

    fun populateDatabase(context: Context) {
        val db = AppDatabase.getDatabase(context)

        runBlocking {
            CoroutineScope(Dispatchers.IO).launch {
                val roomDao = db.roomDao()
                val existingRooms = roomDao.getAllRooms().value

                if (existingRooms.isNullOrEmpty()) {
                    val initialRooms = RoomRepoObject.getRooms()
                    roomDao.insertRooms(initialRooms)
                }
            }
        }
    }
}