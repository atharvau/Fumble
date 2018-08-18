package com.example.atharva.memeapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.felipecsl.asymmetricgridview.library.model.AsymmetricItem;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.varunest.sparkbutton.helpers.Utils;

import java.util.ArrayList;

public class Stories extends AppCompatActivity {
   ArrayList<ModelStories> List = new ArrayList<ModelStories>();
    ArrayList<String> keyList = new ArrayList<String>();

   ArrayList<ModelStories> SEENList = new ArrayList<ModelStories>();
   ArrayList<ModelStories> SeenkeyList = new ArrayList<ModelStories>();

   ArrayList<String> mEssageList = new ArrayList<String>();
    ArrayList<String> Timest = new ArrayList<String>();


    ArrayList<String> RTList = new ArrayList<String>();
  ArrayList<String> keyLIST = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GetMessages();


        DatabaseReference databaseReference1 = FirebaseDatabase.getInstance().getReference();
        databaseReference1.child("PrivateMessages").child(MainActivity.modelInfo.getUid()).child("List").orderByValue().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {



                if(!RTList.contains(dataSnapshot.getKey().toString())) {
                    RTList.add(dataSnapshot.getKey().toString());
                    Timest.add(dataSnapshot.getValue().toString());
                    Toast.makeText(getBaseContext(),dataSnapshot.getValue().toString(),Toast.LENGTH_SHORT).show();
                    CustomMessageAdapter messageAdapter = new CustomMessageAdapter(RTList, Stories.this, Timest);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
                    RecyclerView mess = (RecyclerView) findViewById(R.id.mess);
                    mess.setHasFixedSize(true);
                    mess.setLayoutManager(linearLayoutManager);
                    mess.setAdapter(messageAdapter);
                    messageAdapter.notifyDataSetChanged();

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


    @Override
    protected void onStart() {
        setContentView(R.layout.activity_stories);




        super.onStart();
    }


    public  void GetMessages(){


List.clear();
        keyList.clear();
        // getlsi();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();




        databaseReference.child("PrivateMessages").child(MainActivity.modelInfo.getUid()).child("Recived").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                if(dataSnapshot.exists()&&dataSnapshot.child("seen").getValue().toString().equals("0")) {

                    ModelStories modelStories=new ModelStories();
                    modelStories.setProfilepicture(dataSnapshot.child("profilepicture").getValue().toString());
                    modelStories.setMeme(dataSnapshot.child("meme").getValue().toString());
                    modelStories.setSeen(Integer.parseInt(dataSnapshot.child("seen").getValue().toString()));
                    modelStories.setSender(dataSnapshot.child("sender").getValue().toString());
if(!List.contains(modelStories)) {

    List.add(modelStories);

    keyLIST.add(dataSnapshot.getKey().toString());


    CustomStoryRecAdapter customStoryRecAdapter = new CustomStoryRecAdapter(List, getBaseContext(), keyLIST);
    LinearLayoutManager layoutManager = new LinearLayoutManager(Stories.this, LinearLayoutManager.HORIZONTAL, false);
    RecyclerView stor = (RecyclerView) findViewById(R.id.rec);
    stor.setHasFixedSize(true);

    stor.setLayoutManager(layoutManager);
    stor.setAdapter(customStoryRecAdapter);
    customStoryRecAdapter.notifyDataSetChanged();
    stor.scrollToPosition(List.size()-1);
}
                }


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


                GetMessages();



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