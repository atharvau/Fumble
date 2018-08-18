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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import de.hdodenhof.circleimageview.CircleImageView;

public class PrivateListvieAdapter extends android.widget.BaseAdapter {

    ArrayList<String> RevList = new ArrayList<String>();
    ArrayList<String> Timesp = new ArrayList<String>();
Context ctx;
    public PrivateListvieAdapter(Context ctx, ArrayList<String> revList,ArrayList<String> Timesp) {
        this.ctx = ctx;
        this.RevList = revList;
this.Timesp=Timesp;
    }

    @Override
    public int getCount() {
        return RevList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1=view;

        if (view==null){


            view1 = LayoutInflater.from(ctx).inflate(R.layout.privatelist, viewGroup, false);


            final TextView username=view1.findViewById(R.id.usernamme);
            final CircleImageView circleImageView=view1.findViewById(R.id.imageView);

            final  TextView time=view1.findViewById(R.id.times);



            final ImageView icons=view1.findViewById(R.id.icons);


















            DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();



            databaseReference.child("Profiles").child("Users").child(RevList.get(i)).child("username").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    username.setText(dataSnapshot.getValue().toString());

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });




            databaseReference.child("Profiles").child("Users").child(RevList.get(i)).child("profilepicture").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Glide.with(ctx).load(dataSnapshot.getValue().toString()).into(circleImageView);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });






















        }


;



        return view1;
    }

}
