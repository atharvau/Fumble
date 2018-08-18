package com.example.atharva.memeapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
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

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {
public static String uid;


CircleImageView circleImageView;
TextView username;
TextView name;
TextView status;

LinearLayoutManager linearLayoutManager;
MyAdapter myAdapter;

    public static ArrayList<String> List = new ArrayList<String>();
    public static ArrayList<String> REVList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Intent intent=getIntent();

        uid=intent.getStringExtra("uid");



        ////////////////////////////////////////////////////////
        circleImageView=findViewById(R.id.imageView6);
        username=findViewById(R.id.textView4);
        name=findViewById(R.id.textView6);
        status=findViewById(R.id.textView6);

        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Profiles").child("Users").child(uid).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                name.setText(dataSnapshot.getValue().toString());



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference.child("Profiles").child("Users").child(uid).child("username").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                username.setText(dataSnapshot.getValue().toString());



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });








        databaseReference.child("Profiles").child("Users").child(uid).child("profilepicture").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

Glide.with(Profile.this).load(dataSnapshot.getValue().toString()).into(circleImageView);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });













        final ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        final PagerAdapterProfil adapter = new PagerAdapterProfil
                (getSupportFragmentManager(), 2);
        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_grid1);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_list);



    }





}
