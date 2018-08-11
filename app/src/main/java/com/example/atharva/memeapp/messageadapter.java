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
import android.widget.LinearLayout;
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


public class messageadapter extends RecyclerView.Adapter<messageadapter.MyViewHolder>{
    Context ctx;


    int FinInt;
    public static  ModelInfo modelInfo;
    int iLike=0,i;


    public static String memeoftheday;
    public static String uid;



    ArrayList<ModelMessage> RevList = new ArrayList<ModelMessage>();
    public messageadapter(  ArrayList<ModelMessage> RevList, Context ctx)
    {
        this.RevList =RevList ;
        this.ctx=ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.messagelist,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {



        modelInfo=MainActivity.modelInfo;

        if(RevList.get(position).getUid().equals(MainActivity.modelInfo.getUid())) {

            holder.l.setVisibility(View.GONE);
            holder.r.setVisibility(View.VISIBLE);

            holder.Rmessage.setText(RevList.get(position).getMessage());

            Log.d("A", "onBindViewHolder: "+"YES");
        }
else if (!RevList.get(position).getUid().equals(MainActivity.modelInfo.getUid())){

            holder.r.setVisibility(View.GONE);
            holder.l.setVisibility(View.VISIBLE);

            Log.d("A", "onBindViewHolder: "+"NO");

            holder.Lmessage.setText(RevList.get(position).getMessage());


        }










    }

    @Override
    public int getItemCount() {
        return RevList.size() ;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

TextView Lmessage;
ImageView Limage;
TextView Rmessage;
ImageView Rimage;
LinearLayout l;
LinearLayout r;



        public MyViewHolder(View itemView) {
            super(itemView);


Lmessage=itemView.findViewById(R.id.leftm);
Limage=itemView.findViewById(R.id.leftim);
Rmessage=itemView.findViewById(R.id.rightm);
Rimage=itemView.findViewById(R.id.rightim);
l=itemView.findViewById(R.id.l);
r=itemView.findViewById(R.id.r);


        }
    }






}
