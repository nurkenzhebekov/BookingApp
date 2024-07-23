package com.example.bookingapp.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.bookingapp.data.model.Room

@Dao
interface RoomDao {
    @Query("SELECT * FROM room")
    fun getAllRooms(): LiveData<List<Room>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRooms(rooms: List<Room>)
}