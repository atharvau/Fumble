package com.example.atharva.memeapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class mess extends AppCompatActivity {
String sender ="a";
String reciver="b";
public static ArrayList<modelmessagenew> REVList = new ArrayList<modelmessagenew>();
modelmessagenew modelmessagenewl;
    DatabaseReference databaseReference1;
Boolean b=true;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mess);
databaseReference1=FirebaseDatabase.getInstance().getReference().child("Messages").child("message").child(sender).child(reciver);



        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b=false;
                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
String pushkey= databaseReference.child("Messages").child("message").child(sender).child(reciver).push().getKey();
                databaseReference.child("Messages").child("message").child(sender).child(reciver).child(pushkey).child("reciver").setValue(reciver);
                databaseReference.child("Messages").child("message").child(sender).child(reciver).child(pushkey).child("sender").setValue(sender).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        b=true;
                    }
                });

            }
        });


        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();


                        String pushkey= databaseReference.child("Messages").child("message").child(sender).child(reciver).push().getKey();
                databaseReference.child("Messages").child("message").child(sender).child(reciver).child(pushkey).child("reciver").setValue(sender);

                databaseReference.child("Messages").child("message").child(sender).child(reciver).child(pushkey).child("sender").setValue(reciver).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        b=true;
                    }
                });
            }
        });



    databaseReference1.addChildEventListener(new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            if(b==true) {
            modelmessagenewl = new modelmessagenew();

            if (dataSnapshot.child("sender").exists()) {
                modelmessagenewl.setSender(dataSnapshot.child("sender").getValue().toString());
            }


            if (dataSnapshot.child("reciver").exists()) {
                modelmessagenewl.setReciver(dataSnapshot.child("reciver").getValue().toString());
            }


            if (dataSnapshot.child("pic").exists()) {
                modelmessagenewl.setPic(dataSnapshot.child("pic").getValue().toString());
            }
            REVList.add(modelmessagenewl);


            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mess.this);
            RecyclerView mMessageList = (RecyclerView) findViewById(R.id.recyclerView);

            mMessageList.setHasFixedSize(true);
            mMessageList.setLayoutManager(linearLayoutManager);
            NewMessAd newMessAd = new NewMessAd(REVList, mess.this);

            mMessageList.setAdapter(newMessAd);


        }}

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
