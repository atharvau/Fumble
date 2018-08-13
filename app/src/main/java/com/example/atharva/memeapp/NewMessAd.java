package com.example.atharva.memeapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.varunest.sparkbutton.SparkButton;
import com.wajahatkarim3.clapfab.ClapFAB;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Atharva on 1/16/2018.
 */


public class NewMessAd extends RecyclerView.Adapter<NewMessAd.MyViewHolder>{
    Context ctx;


    int FinInt;
    public static  ModelInfo modelInfo;
    int iLike=0,i;

    String sender ="a";
    String reciver="b";



    public static String memeoftheday;
    public static String uid;


    ArrayList<modelmessagenew> RevList = new ArrayList<modelmessagenew>();
    public NewMessAd(  ArrayList<modelmessagenew> RevList, Context ctx)
    {
        this.RevList =RevList ;
        this.ctx=ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.mess,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


if(RevList.get(position).getSender()!=null) {

    if (RevList.get(position).getSender().equals(sender)) {
        holder.l.setVisibility(View.GONE);

    } else {

        holder.r.setVisibility(View.GONE);


    }
}

    }

    @Override
    public int getItemCount() {
        return RevList.size() ;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

ImageView l,r;
        public MyViewHolder(View itemView) {
            super(itemView);

l=itemView.findViewById(R.id.l);
r=itemView.findViewById(R.id.r);


        }
    }








}
