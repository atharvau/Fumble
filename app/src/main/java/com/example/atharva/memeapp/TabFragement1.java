package com.example.atharva.memeapp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.net.Uri;
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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;


public class TabFragement1 extends Fragment {
    View view;
    ArrayList<String> flist = new ArrayList<String>();

    ArrayList<String> REVlist = new ArrayList<String>();

    //////////////////////////////////////////////////////////////////////////////////
    FirebaseStorage storage;
    StorageReference storageRef,imageRef,DownRef;
    ProgressDialog progressDialog;
    UploadTask uploadTask;
    public Uri down;
    public static String username="athrva";
    DatabaseReference fb;
    /////////////////////////////
    Uri ur1;
    RecyclerView mMessageList;

    //////////////////////////////
    DatabaseReference databaseReference;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        Activity activity = getActivity();

         view=  inflater.inflate(R.layout.recentlist, container, false);
    Loadfriendlist();
       ur1=MainActivity.resultUri;


databaseReference=FirebaseDatabase.getInstance().getReference();













        return view;








    }
    public  void Loadfriendlist(){
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
        databaseReference.child("PrivateMessages").child(MainActivity.modelInfo.getUid()).child("List").orderByValue().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("A", "onChildAdded: "+dataSnapshot.getValue().toString());
                flist.add(dataSnapshot.getKey().toString());

                Toast.makeText(getActivity(),dataSnapshot.getKey().toString(),Toast.LENGTH_SHORT).show();



                REVlist.clear();

                int size2 = flist.size() - 1;
                for (int i = size2; i >= 0; i--) {
                    REVlist.add(flist.get(i));

                    }

                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
                RecyclerView mMessageList= (RecyclerView) view.findViewById(R.id.recyclerview);

                mMessageList.setHasFixedSize(true);
                mMessageList.setLayoutManager(linearLayoutManager);
                flistadapter    myAdapter=new flistadapter(REVlist,getActivity());
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