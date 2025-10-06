package com.example.ecostayretreat.booking.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecostayretreat.R;
import com.example.ecostayretreat.booking.model.Room;

import java.util.List;
import java.util.Locale;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.RoomViewHolder> {

    private final List<Room> roomList;
    private final ClickListener clickListener; // <-- ADD THIS

    // --- INTERFACE FOR CLICK EVENTS ---
    public interface ClickListener {
        void onItemClick(Room room);
    }
    // ------------------------------------

    public RoomAdapter(List<Room> roomList, ClickListener clickListener) { // <-- MODIFY CONSTRUCTOR
        this.roomList = roomList;
        this.clickListener = clickListener; // <-- ADD THIS
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_room, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {
        Room room = roomList.get(position);
        holder.bind(room, clickListener); // <-- PASS LISTENER TO BIND
    }

    @Override
    public int getItemCount() {
        return roomList.size();
    }

    static class RoomViewHolder extends RecyclerView.ViewHolder {
        private final ImageView ivRoomImage;
        private final TextView tvRoomName;
        private final TextView tvRoomPrice;

        public RoomViewHolder(@NonNull View itemView) {
            super(itemView);
            ivRoomImage = itemView.findViewById(R.id.ivRoomImage);
            tvRoomName = itemView.findViewById(R.id.tvRoomName);
            tvRoomPrice = itemView.findViewById(R.id.tvRoomPrice);
        }

        // --- MODIFY BIND METHOD ---
        public void bind(final Room room, final ClickListener clickListener) {
            tvRoomName.setText(room.getName());
            tvRoomPrice.setText(String.format(Locale.getDefault(), "$%.2f / night", room.getPrice()));

            // Set the click listener on the whole item view
            itemView.setOnClickListener(v -> clickListener.onItemClick(room));
        }
        // --------------------------
    }
}
