package com.example.atharva.memeapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TabFragement1 extends Fragment {
    View view;
    ArrayList<String> flist = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        Activity activity = getActivity();

         view=  inflater.inflate(R.layout.recentlist, container, false);
    Loadfriendlist();
        return view;


    }

    public  void Loadfriendlist(){
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Discover").child("Friendlist").child("a").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("A", "onChildAdded: "+dataSnapshot.getValue().toString());
                flist.add(dataSnapshot.getValue().toString());

                Toast.makeText(getActivity(),dataSnapshot.getValue().toString(),Toast.LENGTH_SHORT).show();

                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                RecyclerView mMessageList=  view.findViewById(R.id.recyclerview);

                mMessageList.setHasFixedSize(true);
                mMessageList.setLayoutManager(linearLayoutManager);
                flistadapter   myAdapter=new flistadapter(flist,getActivity());
                mMessageList.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();


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