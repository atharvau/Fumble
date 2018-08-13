package com.example.atharva.memeapp;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class flistadapter extends RecyclerView.Adapter<flistadapter.MyViewHolder>{
    Context ctx;




    ArrayList<String> RevList = new ArrayList<String>();
    public flistadapter(  ArrayList<String> RevList, Context ctx)
    {
        this.RevList =RevList ;
        this.ctx=ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.leftmessage,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Profiles").child("Users").child(RevList.get(position)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

if (dataSnapshot.child("profilepicture").exists()){
    //Glide.with(ctx).load(dataSnapshot.child("profilepicture").getValue().toString()).into(holder.imageView);
                }


                if (dataSnapshot.child("username").exists()){
holder.textView.setText(dataSnapshot.child("username").getValue().toString());
}




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

ImageView imageView;
TextView textView;


        public MyViewHolder(View itemView) {
            super(itemView);

             imageView=itemView.findViewById(R.id.imageView9);
             textView=itemView.findViewById(R.id.usernamme);


        }
    }






}
