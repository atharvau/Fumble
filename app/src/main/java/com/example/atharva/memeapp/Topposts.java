package com.example.atharva.memeapp;

import android.app.slice.Slice;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Topposts extends AppCompatActivity {
    public static ArrayList<String> List = new ArrayList<String>();
    public static ArrayList<String> REVList = new ArrayList<String>();


    modelsorting temp;
LinearLayoutManager linearLayoutManager;
MyAdapter myAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topposts);
        setTitle("Top Posts");

temp=new modelsorting();
GetListSortIt();
//Delete(0);

findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
    }
});
    }









    public  void  GetListSortIt(){
        List.clear();
DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();

        databaseReference.child("Discover").child("likecountposts").orderByValue().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                List.add(dataSnapshot.getKey());

                REVList.clear();

                int size2 = List.size() - 1;
                for (int i = size2; i >= 0; i--) {
                    REVList.add(List.get(i));

                }

                linearLayoutManager=new LinearLayoutManager(Topposts.this);
                RecyclerView mMessageList= (RecyclerView) findViewById(R.id.rec);

                mMessageList.setHasFixedSize(true);
                mMessageList.setLayoutManager(linearLayoutManager);
                myAdapter=new MyAdapter(REVList,Topposts.this);
                mMessageList.setAdapter(myAdapter);


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


    /*public void  Delete(int position){


        final DatabaseReference databaseReference =FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Discover").child("like").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                long h=dataSnapshot.getChildrenCount();
                databaseReference.child("Discover").child("posts").child(dataSnapshot.getKey()).child("like").setValue(h);
                databaseReference.child("Discover").child("likecountposts").child(dataSnapshot.getKey()).setValue(h);



            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                long h=dataSnapshot.getChildrenCount();
                databaseReference.child("Discover").child("posts").child(dataSnapshot.getKey()).child("like").setValue(h);
                databaseReference.child("Discover").child("likecountposts").child(dataSnapshot.getKey()).setValue(h);
                GetListSortIt();

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                long h=dataSnapshot.getChildrenCount();
                databaseReference.child("Discover").child("posts").child(dataSnapshot.getKey()).child("like").setValue(h);
                databaseReference.child("Discover").child("likecountposts").child(dataSnapshot.getKey()).setValue(h);
                databaseReference.child("Discover").child("likecountposts").child(dataSnapshot.getKey()).setValue(h);
                GetListSortIt();

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                long h=dataSnapshot.getChildrenCount();
                databaseReference.child("Discover").child("posts").child(dataSnapshot.getKey()).child("like").setValue(h);
                databaseReference.child("Discover").child("likecountposts").child(dataSnapshot.getKey()).setValue(h);
                GetListSortIt();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }*/




}
