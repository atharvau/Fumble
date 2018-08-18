package com.example.atharva.memeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Groupname extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupname);
        final EditText editText=findViewById(R.id.editText2);

        findViewById(R.id.button8).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

             final String groupname=editText.getText().toString();

                DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
databaseReference.child("Group").child(groupname).child("name").setValue(groupname).addOnSuccessListener(new OnSuccessListener<Void>() {
    @Override
    public void onSuccess(Void aVoid) {


        Intent intent=new Intent(Groupname.this,GroupMaker.class);
        intent.putExtra("groupname",groupname);


        startActivity(intent);






    }
});









            }
        });







    }
}
