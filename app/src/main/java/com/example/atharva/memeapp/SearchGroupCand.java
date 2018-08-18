package com.example.atharva.memeapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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

public class SearchGroupCand extends android.widget.BaseAdapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    Context ctx;
    ArrayList<String> RevList = new ArrayList<String>();

String groupname;



    //////////////////////////////////////////////////////////////////////////////////
    FirebaseStorage storage;
    StorageReference storageRef,imageRef,DownRef;
    ProgressDialog progressDialog;
    UploadTask uploadTask;
    public Uri down;
    public static String username="athrva";
    DatabaseReference fb;
    /////////////////////////////

    public static String abix;

    public SearchGroupCand(Context ctx, ArrayList<String> revList,String groupname) {
        this.ctx = ctx;
        this.RevList = revList;
this.groupname=groupname;

    }

    @Override
    public int getCount() {
        return RevList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View view1=view;

        if (view==null){

            final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();


            view1 = LayoutInflater.from(ctx).inflate(R.layout.searchlistmessage, viewGroup, false);


            final TextView username=view1.findViewById(R.id.usernamme);
            final ImageView imageView=view1.findViewById(R.id.imageView);



            DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference();
            databaseReference.child("Profiles").child("Users").child(RevList.get(i)).child("username").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    username.setText(dataSnapshot.getValue().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });




            databaseReference.child("Profiles").child("Users").child(RevList.get(i)).child("profilepicture").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
Glide.with(ctx).load(dataSnapshot.getValue().toString()).into(imageView);





                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }

            });


            username.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
  DatabaseReference databaseReference2=FirebaseDatabase.getInstance().getReference();
databaseReference2.child("Groups").child(groupname).child("List").child(RevList.get(i)).setValue(RevList.get(i));
databaseReference.child("GroupsList").child(RevList.get(i)).child(groupname).setValue(groupname);









                }
            });









        }












        return view1;
    }








}
