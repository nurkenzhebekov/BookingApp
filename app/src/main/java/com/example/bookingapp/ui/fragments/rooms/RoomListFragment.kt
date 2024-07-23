package com.example.bookingapp.ui.fragments.rooms

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookingapp.databinding.FragmentRoomListBinding
import com.example.bookingapp.ui.fragments.room.BookViewModel

class RoomListFragment : Fragment() {

    private var _binding: FragmentRoomListBinding? = null
    private val binding get() = _binding!!

    private val roomViewModel: BookViewModel by viewModels()
    private lateinit var roomAdapter: RoomAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRoomListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        roomAdapter = RoomAdapter(emptyList()) { room ->
            val action = RoomListFragmentDirections.actionRoomListFragmentToRoomDetailFragment(room.id)
            findNavController().navigate(action)
        }

        binding.rvRooms.apply {
            adapter = roomAdapter
            layoutManager = LinearLayoutManager(context)
        }

        roomViewModel.rooms.observe(viewLifecycleOwner, Observer { rooms ->
            roomAdapter = RoomAdapter(rooms) { room ->
                val action = RoomListFragmentDirections.actionRoomListFragmentToRoomDetailFragment(room.id)
                findNavController().navigate(action)
            }
            binding.rvRooms.adapter = roomAdapter
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}