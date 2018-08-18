package com.example.atharva.memeapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class Story extends AppCompatActivity {

    DatabaseReference databaseReference;

ArrayList<String> List=new ArrayList<String>();
    ArrayList<String> REVLIST=new ArrayList<String>();

    ArrayList<String> Stmp=new ArrayList<String>();



    ArrayList<String> Stry=new ArrayList<String>();
    ArrayList<String> h=new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        getLIST();
        GetStories();


    }


    public void getLIST() {

List.clear();
REVLIST.clear();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("PrivateMessages").child(MainActivity.modelInfo.getUid()).child("List");


        Query firebaseSearchQuery = databaseReference;

        firebaseSearchQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                if(!List.contains(dataSnapshot.getKey())&&!Stmp.contains(dataSnapshot.getValue().toString())) {


                    Toast.makeText(getBaseContext(), dataSnapshot.getValue().toString(), Toast.LENGTH_SHORT).show();

                    Stmp.add(dataSnapshot.getValue().toString());
                    List.add(dataSnapshot.getKey().toString());
                    int size2 = List.size() - 1;
                    for (int i = size2; i>=0; i--) {
                        REVLIST.add(List.get(i));


                    }


                    ListView listView = findViewById(R.id.kl);

                    PrivateListvieAdapter privateListvieAdapter = new PrivateListvieAdapter(Story.this, List, Stmp);
                    listView.setAdapter(privateListvieAdapter);

                    privateListvieAdapter.notifyDataSetChanged();


                }


            }








            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {



                getLIST();
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


    public void GetStories(){


        databaseReference = FirebaseDatabase.getInstance().getReference().child("PrivateMessages").child(MainActivity.modelInfo.getUid()).child("Recived");
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {





                if(!Stry.contains(dataSnapshot.getKey().toString())){




                }







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