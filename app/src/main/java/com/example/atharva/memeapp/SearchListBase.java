package com.example.atharva.memeapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchListBase extends android.widget.BaseAdapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    Context ctx;
    ArrayList<String> RevList = new ArrayList<String>();

    public SearchListBase(Context ctx, ArrayList<String> revList) {
        this.ctx = ctx;
        this.RevList = revList;

    }

    @Override
    public int getCount() {
        return RevList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1=view;

        if (view==null){

            DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();


            view1 = LayoutInflater.from(ctx).inflate(R.layout.leftmessage, viewGroup, false);

            final TextView textView=view1.findViewById(R.id.usernamme);
            final TextView name=view1.findViewById(R.id.name);
            final ImageView imageView=view1.findViewById(R.id.imageView);


            databaseReference.child("Profiles").child("Users").child(RevList.get(i)).child("username").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


               textView.setText(dataSnapshot.getValue().toString());


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            databaseReference.child("Profiles").child("Users").child(RevList.get(i)).child("name").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    name.setText(dataSnapshot.getValue().toString());


                }






                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            databaseReference.child("Profiles").child("Users").child(RevList.get(i)).child("profilepicture").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    Glide.with(ctx).load(dataSnapshot.getValue().toString()).into(imageView);



                }






                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });




        }






        return view1;
    }


}
