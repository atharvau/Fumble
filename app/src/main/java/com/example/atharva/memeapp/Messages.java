package com.example.atharva.memeapp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
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
s md;
    ChatAdapter chatAdapter;
BaseAdapter baseAdapter1;
    int i=-1;

    ListView list;

public static ArrayList<ModelMessage> REVList = new ArrayList<ModelMessage>();
    public static ArrayList<String> gh = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

editText=findViewById(R.id.editText);


        ListView listView=findViewById(R.id.list);
       baseAdapter1=new BaseAdapter(Messages.this,REVList);
        listView.setAdapter(baseAdapter1);
findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {












        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();



        pushkey= databaseReference.child("Message").child("a").child("b").push().getKey();
        String mas=editText.getText().toString();
        databaseReference.child("Message").child("a").child("b").child(pushkey).child("message").setValue(mas);
        databaseReference.child("Message").child("a").child("b").child(pushkey).child("uid").setValue("a").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                baseAdapter1.notifyDataSetChanged();
            }
        });






    }
});
       // GetMessages();


    }

    public  void GetMessages(){
REVList.clear();

         DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();

databaseReference.child("Message").child("a").child("b").addChildEventListener(new ChildEventListener() {
    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
i++;

     ModelMessage  modelMessage=new ModelMessage("","","");


        modelMessage.setMessage(dataSnapshot.child("message").getValue().toString());
if (dataSnapshot.child("uid").exists()) modelMessage.setUid(dataSnapshot.child("uid").getValue().toString());
modelMessage.setProfilepic("");
        String a;

REVList.add(modelMessage);

if(dataSnapshot.child("uid").exists()) {
    a = dataSnapshot.child("uid").getValue().toString();

if(a!=null){

    if(a.equals("a")){


        ListView listView=findViewById(R.id.list);
        baseAdapter1=new BaseAdapter(Messages.this,REVList);
        listView.setAdapter(baseAdapter1);




baseAdapter1.notifyDataSetChanged();

    }
    else if(a.equals("b")) {

        ListView listView=findViewById(R.id.list);
        baseAdapter1=new BaseAdapter(Messages.this,REVList);
        listView.setAdapter(baseAdapter1);

        baseAdapter1.notifyDataSetChanged();

    }
}

}





        final ScrollView scroll=findViewById(R.id.sc);


        scroll.post(new Runnable() {
            @Override
            public void run() {

                scroll.fullScroll(View.FOCUS_DOWN);
            }
        });












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

    @Override
    protected void onStart() {


       GetMessages();
        super.onStart();
    }
}
