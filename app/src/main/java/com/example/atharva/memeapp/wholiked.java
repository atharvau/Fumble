package com.example.atharva.memeapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class wholiked extends AppCompatActivity {
String key;
DatabaseReference databaseReference;
customwholikeadapter myAdapter;
    public static ArrayList<String> List = new ArrayList<String>();
    public static ArrayList<String> UList = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wholiked);


        Intent intent=getIntent();
        key=intent.getStringExtra("key");

        getLikers();
    }

    public  void getLikers(){
        List.clear();
        UList.clear();
        databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Discover").child("like").child(key).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Log.d("A", "onChildAdded: "+dataSnapshot.getValue().toString());
List.add(dataSnapshot.getKey().toString());

                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(wholiked.this);
                RecyclerView mMessageList= (RecyclerView) findViewById(R.id.rec);


UList.add(dataSnapshot.getValue().toString());

                mMessageList.setHasFixedSize(true);
                mMessageList.setLayoutManager(linearLayoutManager);
                myAdapter=new customwholikeadapter(List,UList,wholiked.this);
                mMessageList.setAdapter(myAdapter);
                TextView textView=findViewById(R.id.textView);
                textView.setText(UList.size()+"");


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });






    }

}

