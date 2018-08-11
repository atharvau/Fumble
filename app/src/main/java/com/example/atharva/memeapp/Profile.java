package com.example.atharva.memeapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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


        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Profiles").child("Users").child(uid).child("username").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

           username.setText(dataSnapshot.getValue().toString());
setTitle(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        databaseReference.child("Profiles").child("Users").child(uid).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                name.setText(dataSnapshot.getValue().toString());

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
        getList();

    }




    public  void getList(){
        List.clear();

      DatabaseReference  mdb=FirebaseDatabase.getInstance().getReference();
        mdb.child("Userposts").child(uid).child("list").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                REVList.clear();

                String a=dataSnapshot.getValue().toString();
                List.add(a);


                int size2 = List.size() - 1;
                for (int i = size2; i >= 0; i--) {
                    REVList.add(List.get(i));

                }



                linearLayoutManager=new LinearLayoutManager(Profile.this);
                RecyclerView mMessageList= (RecyclerView) findViewById(R.id.recyclerview);

                mMessageList.setHasFixedSize(true);
                mMessageList.setLayoutManager(linearLayoutManager);
                myAdapter=new MyAdapter(REVList,Profile.this);
                mMessageList.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();
                Log.d("A", "CRYT"+REVList.size()+"");
                mygridadapter adapter = new mygridadapter(Profile.this,REVList);
                GridView  grid=(GridView)findViewById(R.id.grid);
                grid.setAdapter(adapter);





            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                REVList.clear();

                String a=dataSnapshot.getValue().toString();
                List.add(a);

                int size2 = List.size() - 1;
                for (int i = size2; i >= 0; i--) {
                    REVList.add(List.get(i));

                }



                linearLayoutManager=new LinearLayoutManager(Profile.this);
                RecyclerView mMessageList= (RecyclerView) findViewById(R.id.recyclerview);

                mMessageList.setHasFixedSize(true);
                mMessageList.setLayoutManager(linearLayoutManager);
                myAdapter=new MyAdapter(REVList,Profile.this);
                mMessageList.setAdapter(myAdapter);


                mygridadapter adapter = new mygridadapter(Profile.this,REVList);
                GridView  grid=(GridView)findViewById(R.id.grid);
                grid.setAdapter(adapter);




                Log.d("A", REVList.size()+"");
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                REVList.clear();

                String a=dataSnapshot.getValue().toString();
                List.add(a);


                int size2 = List.size() - 1;
                for (int i = size2; i >= 0; i--) {
                    REVList.add(List.get(i));

                }



                linearLayoutManager=new LinearLayoutManager(Profile.this);
                RecyclerView mMessageList= (RecyclerView) findViewById(R.id.recyclerview);

                mMessageList.setHasFixedSize(true);
                mMessageList.setLayoutManager(linearLayoutManager);
                myAdapter=new MyAdapter(REVList,Profile.this);
                mMessageList.setAdapter(myAdapter);

                Log.d("A", REVList.size()+"");
                myAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

findViewById(R.id.imageView8).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        findViewById(R.id.recyclerview).setVisibility(View.GONE);
        findViewById(R.id.grid).setVisibility(View.VISIBLE);
        findViewById(R.id.lin1).setVisibility(view.VISIBLE);

    }
});


        findViewById(R.id.imageView7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                findViewById(R.id.recyclerview).setVisibility(View.VISIBLE);
                findViewById(R.id.grid).setVisibility(View.GONE);
                findViewById(R.id.lin1).setVisibility(View.GONE);


            }
        });



    }

}
