package com.example.bookingapp.ui.fragments.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.bookingapp.data.AppDatabase
import com.example.bookingapp.data.repository.BookingRepository
import com.example.bookingapp.data.repository.RoomRepository
import com.example.bookingapp.data.model.Booking
import com.example.bookingapp.data.model.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Date

class BookViewModel(application: Application) : AndroidViewModel(application) {

    private val bookingDao = AppDatabase.getDatabase(application).bookingDao()
    private val roomDao = AppDatabase.getDatabase(application).roomDao()
    private val bookingRepository = BookingRepository(bookingDao)
    private val roomRepository = RoomRepository(roomDao)

    val rooms: LiveData<List<Room>> = roomRepository.getRooms()

    private val _bookingStatus = MutableLiveData<Boolean>()
    val bookingStatus: LiveData<Boolean> get() = _bookingStatus

    private val _bookingDate = MutableLiveData<Date?>()
    val bookingDate: MutableLiveData<Date?> get() = _bookingDate

    fun getBookingsForRoom(roomId: Int): LiveData<List<Booking>> {
        return bookingRepository.getBookingsForRoom(roomId)
    }

    fun insertBooking(booking: Booking) {
        viewModelScope.launch(Dispatchers.IO) {
            bookingRepository.insertBooking(booking)
            _bookingStatus.postValue(true)
            _bookingDate.postValue(Date(booking.date))
        }
    }

    fun deleteBooking(booking: Booking) {
        viewModelScope.launch(Dispatchers.IO) {
            bookingRepository.deleteBooking(booking)
            _bookingStatus.postValue(false)
            _bookingDate.postValue(null)
        }
    }
}