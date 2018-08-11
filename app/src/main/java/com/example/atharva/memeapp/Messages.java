package com.example.atharva.memeapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Messages extends AppCompatActivity {
EditText editText;
public static ModelInfo modelInfo;
Button send;
LinearLayoutManager linearLayoutManager;
messageadapter myAdapter;
    BaseAdapter baseAdapter;
public static String pushkey;
    RecyclerView mMessageList;

    ListView list;

public static ArrayList<ModelMessage> REVList = new ArrayList<ModelMessage>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

editText=findViewById(R.id.editText);
send=findViewById(R.id.button4);
        linearLayoutManager=new LinearLayoutManager(Messages.this);
         mMessageList= (RecyclerView) findViewById(R.id.mMessageList);

        mMessageList.setHasFixedSize(true);

        mMessageList.setLayoutManager(linearLayoutManager);
        myAdapter=new messageadapter(REVList,Messages.this);
        mMessageList.setAdapter(myAdapter);


send.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
        pushkey= databaseReference.child("Message").child("a").child("b").push().getKey();
        String mas=editText.getText().toString();
        databaseReference.child("Message").child("a").child("b").child(pushkey).child("message").setValue(mas);
        databaseReference.child("Message").child("a").child("b").child(pushkey).child("uid").setValue(MainActivity.modelInfo.getUid());


    }
});
findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
        pushkey= databaseReference.child("Message").child("a").child("b").push().getKey();
        String mas=editText.getText().toString();
        databaseReference.child("Message").child("a").child("b").child(pushkey).child("message").setValue(mas);
        databaseReference.child("Message").child("a").child("b").child(pushkey).child("uid").setValue("b");



    }
});
        GetMessages();








    }

    public  void GetMessages(){
REVList.clear();
         DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();

databaseReference.child("Message").child("a").child("b").addChildEventListener(new ChildEventListener() {
    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


     ModelMessage  modelMessage=new ModelMessage("","","");


        modelMessage.setMessage(dataSnapshot.child("message").getValue().toString());
if (dataSnapshot.child("uid").exists()) modelMessage.setUid(dataSnapshot.child("uid").getValue().toString());
modelMessage.setProfilepic("");
REVList.add(modelMessage);
        mMessageList.setLayoutManager(linearLayoutManager);
        myAdapter=new messageadapter(REVList,Messages.this);
        mMessageList.setAdapter(myAdapter);
myAdapter.notifyDataSetChanged();
        mMessageList.scrollToPosition(REVList.size()-1);
         baseAdapter=new BaseAdapter(Messages.this,REVList);

        list=(ListView)findViewById(R.id.list);
        list.setAdapter(baseAdapter);



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
