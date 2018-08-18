package com.example.atharva.memeapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL;

import de.hdodenhof.circleimageview.CircleImageView;


public class FragemnetPro3 extends Fragment {
    View view;

    String uid;
    CircleImageView circleImageView;
    TextView username;
    TextView name;
    TextView status;

    LinearLayoutManager linearLayoutManager;
    MyAdapter myAdapter;

    public static ArrayList<String> GList = new ArrayList<String>();
    public static ArrayList<String> REVList = new ArrayList<String>();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        Activity activity = getActivity();

         view=  inflater.inflate(R.layout.layfrag2, container, false);

        getList();






        return view;





    }
    public  void getList(){





        DatabaseReference databaseReferencen=FirebaseDatabase.getInstance().getReference();
        databaseReferencen.child("Grouplist").child(MainActivity.modelInfo.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {



                if(!GList.contains(dataSnapshot.getValue().toString()))
                {

                    GList.add(dataSnapshot.getValue().toString());


                    Toast.makeText(getContext(),dataSnapshot.getValue().toString(),Toast.LENGTH_SHORT).show();


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