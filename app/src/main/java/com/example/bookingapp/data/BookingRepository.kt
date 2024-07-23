package com.example.bookingapp.data

import androidx.lifecycle.LiveData
import com.example.bookingapp.data.model.Booking
import com.example.bookingapp.data.model.Room

class BookingRepository(private val bookingDao: BookingDao) {

    fun getBookingsForRoom(roomId: Int): LiveData<List<Booking>> {
        return bookingDao.getBookingsForRoom(roomId)
    }

    suspend fun insertBooking(booking: Booking) {
        bookingDao.insertBooking(booking)
    }

    suspend fun deleteBooking(booking: Booking) {
        bookingDao.deleteBooking(booking)
    }
}