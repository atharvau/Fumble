package com.example.atharva.memeapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class GroupMaker extends AppCompatActivity {
    ArrayList<String> Slist = new ArrayList<String>();
    ArrayList<String> ADDEDLIST = new ArrayList<String>();

    DatabaseReference UserDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_maker);


Intent intent=getIntent();
final String groupname=intent.getStringExtra("groupname");
setTitle("Create New Group");













        UserDatabase = FirebaseDatabase.getInstance().getReference("Profiles").child("Users");





        EditText searchtext=findViewById(R.id.searchtext);


        searchtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {



                Slist.clear();
                String searchText=editable.toString();

                Query firebaseSearchQuery = UserDatabase.orderByChild("name").startAt(searchText.toLowerCase()).endAt(searchText.toLowerCase() + "\uf8ff");



                firebaseSearchQuery.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {




                        if(!dataSnapshot.getKey().toString().equals(MainActivity.modelInfo.getUid())&&!Slist.contains(dataSnapshot.getKey().toString())&&!ADDEDLIST.contains(dataSnapshot.getKey().toString())) {


                            Slist.add(dataSnapshot.getKey().toString());


                            ListView listView=findViewById(R.id.li);
                            SearchGroupCand searchGroupCand=new SearchGroupCand(GroupMaker.this,Slist,groupname);

listView.setAdapter(searchGroupCand);
searchGroupCand.notifyDataSetChanged();



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
        });




        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Groups").child(groupname).child("List").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(!ADDEDLIST.contains(dataSnapshot.getValue().toString())&&!dataSnapshot.getValue().toString().equals(MainActivity.modelInfo.getUid()))
                {
                   ADDEDLIST.add(dataSnapshot.getValue().toString());
                   ListView listView=findViewById(R.id.li2);
                   GroupAdded groupAdded=new GroupAdded(GroupMaker.this,ADDEDLIST,groupname);
                   listView.setAdapter(groupAdded);
                   groupAdded.notifyDataSetChanged();



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
