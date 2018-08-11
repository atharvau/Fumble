package com.example.atharva.memeapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.varunest.sparkbutton.SparkButton;
import com.wajahatkarim3.clapfab.ClapFAB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Atharva on 1/16/2018.
 */

/**
 * Created by Atharva on 1/16/2018.
 */


public class customwholikeadapter extends RecyclerView.Adapter<customwholikeadapter.MyViewHolder>  {
    Context ctx;


    int FinInt;
    public static  ModelInfo modelInfo;
    int iLike=0,i;




    ArrayList<String> RevList = new ArrayList<String>();
    ArrayList<String> Ulist = new ArrayList<String>();

    public customwholikeadapter(ArrayList<String> RevList,ArrayList<String> Ulist, Context ctx)
    {
        this.RevList =RevList ;
        this.ctx=ctx;
        this.Ulist=Ulist;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listlike,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.textView3.setText(RevList.get(position)+"");

DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
databaseReference.child("Profiles").child("Users").child(Ulist.get(position)).child("profilepicture").addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
      if(dataSnapshot.exists())  Glide.with(ctx).load(dataSnapshot.getValue().toString()).into(holder.img);
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});

    }

    @Override
    public int getItemCount() {
        return RevList.size() ;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
TextView textView3;
ImageView img;
        public MyViewHolder(View itemView) {
            super(itemView);
textView3=itemView.findViewById(R.id.textView3);
img=itemView.findViewById(R.id.imageView3);

        }
    }




}

