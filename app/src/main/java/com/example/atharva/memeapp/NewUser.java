package com.example.atharva.memeapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class NewUser extends AppCompatActivity {
    MaterialEditText email, name, username, password;
    CircleImageView proimage;
    public static ArrayList<String> usernames = new ArrayList<String>();


    String uid,photourl,Sname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);


///////////////////////////////////////





        email = findViewById(R.id.email);
        name = findViewById(R.id.name);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);

        proimage = findViewById(R.id.propic);

        FirebaseUser math = FirebaseAuth.getInstance().getCurrentUser();
        name.setText(math.getDisplayName());
        email.setText(math.getEmail());
        Glide.with(this).load(math.getPhotoUrl()).into(proimage);
         photourl = String.valueOf(math.getPhotoUrl());



        uid=math.getUid();
Sname=math.getDisplayName().toLowerCase();




        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Profiles").child("Lists").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()) usernames.add(dataSnapshot.getValue().toString());
                Toast.makeText(getBaseContext(),dataSnapshot.getValue().toString(),Toast.LENGTH_SHORT).show();

                if(dataSnapshot.getChildrenCount()==usernames.size())
                {//dialog.dismiss();
                    // moduli();
                }


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()) usernames.add(dataSnapshot.getValue().toString());

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) usernames.add(dataSnapshot.getValue().toString());

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
























        findViewById(R.id.Next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String Susername;

                Susername = username.getText().toString();


                int flag = 0;
                int a = usernames.size();
                for (int i = 0; i < a; i++) {

                    if (Susername.equals(usernames.get(i))) {
                        flag = 1;
                    }
                    if (flag == 1) {

                        username.setError("Username Already Exits!");
                        Toast.makeText(getBaseContext(),"A",Toast.LENGTH_SHORT).show();

                    }
                    else {


                        DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference();
                        databaseReference1.child("Profiles").child("Users").child(uid).child("name").setValue(Sname);
                        databaseReference1.child("Profiles").child("Users").child(uid).child("username").setValue(Susername);
                        databaseReference1.child("Profiles").child("Users").child(uid).child("profilepicture").setValue(photourl).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid){
                                Intent intent=new Intent(NewUser.this,MainActivity.class);
                                startActivity(intent);


                            }
                        });
















                    }


                }


            }
        });


    }


}








