package com.example.bookingapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bookingapp.data.dao.BookingDao
import com.example.bookingapp.data.dao.RoomDao
import com.example.bookingapp.data.model.Booking
import com.example.bookingapp.data.model.Room

@Database(entities = [Booking::class, Room::class], version = 1, exportSchema = false)

abstract class AppDatabase: RoomDatabase() {
    abstract fun bookingDao(): BookingDao
    abstract fun roomDao(): RoomDao

    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}