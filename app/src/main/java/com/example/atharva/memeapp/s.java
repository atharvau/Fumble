package com.example.atharva.memeapp;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class s extends RecyclerView.Adapter {




    private ArrayList<ModelMessage> Revlist;
    Context mContext;

    public s(ArrayList<ModelMessage> revlist, Context mContext) {
        Revlist = revlist;
        this.mContext = mContext;
    }

    public static class TextTypeViewHolder extends RecyclerView.ViewHolder {

        TextView txtType;

        public TextTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.message);
        }
    }

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder {

        TextView txtType;

        public ImageTypeViewHolder(View itemView) {
            super(itemView);

            this.txtType = (TextView) itemView.findViewById(R.id.message);
        }
    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        switch (viewType) {
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.leftmessage, parent, false);
                return new TextTypeViewHolder(view);
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rightmessage, parent, false);
                return new ImageTypeViewHolder(view);


        }
        return null;
    }

    @Override
    public int getItemViewType(int position) {

        switch (Revlist.get(position).getUid()) {
            case "a":
                return 1;
            case "b":
                return 2;

            default:
                return -1;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int listPosition) {

String uid=Revlist.get(listPosition).getUid();
if (uid != null) {
            switch (uid) {

                case "a" :
                    ((TextTypeViewHolder) holder).txtType.setText(Revlist.get(listPosition).getMessage());

                    break;
                case "b":

                    ((ImageTypeViewHolder) holder).txtType.setText(Revlist.get(listPosition).getMessage());
                    break;


            }
        }
    }

    @Override
    public int getItemCount() {
return  Revlist.size();
    }





}
