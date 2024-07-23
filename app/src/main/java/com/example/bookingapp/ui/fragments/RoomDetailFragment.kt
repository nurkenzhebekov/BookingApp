package com.example.bookingapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.bookingapp.R
import com.example.bookingapp.data.RoomRepoObject
import com.example.bookingapp.data.model.Booking
import com.example.bookingapp.databinding.FragmentRoomDetailBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class RoomDetailFragment : Fragment() {

    private var _binding: FragmentRoomDetailBinding? = null
    private val binding get() = _binding!!

    private val args: RoomDetailFragmentArgs by navArgs()
    private val bookViewModel: BookViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoomDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val roomId = args.roomId
        bookViewModel.getBookingsForRoom(roomId).observe(viewLifecycleOwner, Observer { bookings ->
            if (bookings.isNotEmpty()) {
                binding.tvRoomBook.text = "Booked"
                binding.tvRoomBookDate.visibility = View.VISIBLE
                binding.tvRoomBookDate.text = formatDate(bookings[0].date)
            } else {
                binding.tvRoomBook.text = "Available"
                binding.tvRoomBookDate.visibility = View.GONE
            }
        })

        binding.btnBook.setOnClickListener {
            BookingDatePickerDialogFragment { year, month, day ->
                val calendar = Calendar.getInstance()
                calendar.set(year,  month, day)
                val bookingDate = calendar.time

                val booking = Booking(roomId = roomId, date = bookingDate.time)
                bookViewModel.insertBooking(booking)
            }.show(parentFragmentManager, "BookingDatePickerDialogFragment")
        }

        binding.btnCancelBooking.setOnClickListener {
            val bookings = bookViewModel.getBookingsForRoom(roomId).value
            if (!bookings.isNullOrEmpty()) {
                val bookingToCancel = bookings.firstOrNull { it.roomId == roomId }
                if (bookingToCancel != null) {
                    bookViewModel.deleteBooking(bookingToCancel)
                    Toast.makeText(requireContext(), "Booking canceled", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(requireContext(), "No booking to cancel", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(requireContext(), "No booking to cancel", Toast.LENGTH_SHORT).show()
            }
        }

        bookViewModel.bookingStatus.observe(viewLifecycleOwner, Observer { booked ->
            if (booked) {
                binding.tvRoomBook.text = "Booked"
                binding.tvRoomBookDate.visibility = View.VISIBLE
            } else {
                binding.tvRoomBook.text = "Available"
                binding.tvRoomBookDate.visibility = View.GONE
            }
        })

        bookViewModel.bookingDate.observe(viewLifecycleOwner, Observer { date ->
            binding.tvRoomBookDate.text = date?.toString() ?: ""
        })

        bookViewModel.rooms.observe(viewLifecycleOwner, Observer { rooms ->
            val room = rooms.find { it.id == roomId }
            room?.let {
                binding.ivRoom.setImageResource(room.imageResId)
                binding.tvRoomDetailsDesc.text = room.name
                binding.tvRoomDetails.text = room.description
            }
        })
    }

    private fun formatDate(dateInMillis: Long): String {
        val date = Date(dateInMillis)
        val format = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return format.format(date)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}