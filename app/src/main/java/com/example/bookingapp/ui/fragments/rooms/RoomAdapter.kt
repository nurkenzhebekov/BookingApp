package com.example.bookingapp.ui.fragments.rooms

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookingapp.R
import com.example.bookingapp.data.model.Room

class RoomAdapter(private var rooms: List<Room>, private val onItemClick: (Room) -> Unit) :
    RecyclerView.Adapter<RoomAdapter.RoomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_rooms, parent, false)
        return RoomViewHolder(view)
    }

    override fun getItemCount() = rooms.size

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {
        val room = rooms[position]
        holder.bind(room, onItemClick)
    }

    fun updateRooms(newRooms: List<Room>) {
        rooms = newRooms
        notifyDataSetChanged()
    }

    class RoomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val roomName: TextView = itemView.findViewById(R.id.tv_room_name)
        private val roomImgView: ImageView = itemView.findViewById(R.id.iv_room)

        fun bind(room: Room, onItemClick: (Room) -> Unit) {
            roomName.text = room.name
            Glide.with(itemView.context)
                .load(room.imageResId)
                .into(roomImgView)
            itemView.setOnClickListener { onItemClick(room) }
        }
    }
}