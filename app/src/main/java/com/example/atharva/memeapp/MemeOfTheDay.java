package com.example.atharva.memeapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.varunest.sparkbutton.SparkButton;

public class MemeOfTheDay extends AppCompatActivity {
public static String postkey;

public static int iLike=0;
   ModelInfo modelInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meme_of_the_day);


        Intent intent=getIntent();
        postkey=intent.getStringExtra("postkey");

        LinearLayout linearLayout=findViewById(R.id.inc);

modelInfo=MainActivity.modelInfo;


        final ImageView imageView=linearLayout.findViewById(R.id.imageView);
final ImageView img=linearLayout.findViewById(R.id.img);
final TextView caption=linearLayout.findViewById(R.id.caption);
final TextView username=linearLayout.findViewById(R.id.username);
final SparkButton like=linearLayout.findViewById(R.id.likeButton);

final TextView counts=linearLayout.findViewById(R.id.textView5);


        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Discover").child("posts").child(postkey).child("caption").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
if (dataSnapshot.exists()) caption.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        databaseReference.child("Discover").child("posts").child(postkey).child("meme").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Glide.with(MemeOfTheDay.this).load(dataSnapshot.getValue().toString()).into(img);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        databaseReference.child("Discover").child("posts").child(postkey).child("username").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) username.setText(dataSnapshot.getValue().toString());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







        databaseReference.child("Discover").child("posts").child(postkey).child("profilepic").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Glide.with(MemeOfTheDay.this).load(dataSnapshot.getValue().toString()).into(imageView);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        databaseReference.child("Discover").child("like").child(postkey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



      like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iLike++;



                if(iLike%2!=0) {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("Discover").child("like").child(postkey).child(modelInfo.getUsername()).setValue(modelInfo.getUid());
                    like.playAnimation();
                  like.setChecked(true);
                    DatabaseReference mdb=FirebaseDatabase.getInstance().getReference();
                    mdb.child("Discover").child("like").child(postkey).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });






                }
                else
                if(iLike%2==0) {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("Discover").child("like").child(postkey).child(modelInfo.getUsername()).removeValue();
                    like.playAnimation();
                   like.setChecked(false);







                }









            }
        });

    }
}
