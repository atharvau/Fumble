package com.example.atharva.memeapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ViewHolder> {
String uid;

    Context context = null;
    ArrayList<ModelMessage> chatList = null;
    private final int SENT_MESSAGE = 0, RECEIVED_MESSAGE = 1,RF=69;

    public ChatAdapter(Context context, ArrayList<ModelMessage> chatList) {
        this.context = context;
        this.chatList = chatList;

    }

    /***
     *
     * @param position
     * @return the view type of the item at the position for the
     * purpose of recycling view
     *
     * By default it returns zero showing a single view type for the adapter.
     */
    @Override
    public int getItemViewType(int position) {
        if (chatList.get(position).getUid().equals(MainActivity.modelInfo.getUid())) {
            return SENT_MESSAGE;
        } else
            return RECEIVED_MESSAGE;

    }

    @Override
    public ChatAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        //Based on view type decide which type of view to supply with viewHolder
        switch (viewType) {
            case SENT_MESSAGE:
                view = LayoutInflater.from(parent.getContext()).inflate					(R.layout.leftmessage, parent, false);
                break;

            case RECEIVED_MESSAGE:
                view = LayoutInflater.from(parent.getContext()).inflate					(R.layout.rightmessage, parent, false);
                break;
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatAdapter.ViewHolder holder, int position) {
        holder.texttosend.setText(chatList.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    /*
     * Here we have kept ID's of all the child row elements same.
     * But we can also create to different viewHolder classes
     * for different child rows.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView texttosend;
        public ViewHolder(View itemView) {
            super(itemView);
            texttosend = (TextView) itemView.findViewById(R.id.message);
        }
    }
}