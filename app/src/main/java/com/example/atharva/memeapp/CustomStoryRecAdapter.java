package com.example.atharva.memeapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.annotation.GlideOption;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.varunest.sparkbutton.SparkButton;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Atharva on 1/16/2018.
 */


public class CustomStoryRecAdapter extends RecyclerView.Adapter<CustomStoryRecAdapter.MyViewHolder>{
Context ctx;


int FinInt;
public static  ModelInfo modelInfo;
int iLike=0,i;


public static String memeoftheday;
public static String uid;

    ArrayList<ModelStories> RevList = new ArrayList<ModelStories>();
    public static ArrayList<String> keyLIST = new ArrayList<String>();

    public CustomStoryRecAdapter(ArrayList<ModelStories> RevList, Context ctx,ArrayList<String> keyLIST)
    {
        this.RevList =RevList ;
this.ctx=ctx;
this.keyLIST=keyLIST;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.messagecard,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        ////////////////////////////////////////////////////////////////////
modelInfo=MainActivity.modelInfo;

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        if(RevList.get(position).getSeen()==0) {



            Picasso.get().load(RevList.get(position).getMeme()).transform(new jp.wasabeef.picasso.transformations.BlurTransformation(ctx, 1, 100)).into(holder.blurred);



            databaseReference.child("Profiles").child("Users").child(RevList.get(position).getSender()).child("profilepicture").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   Glide.with(ctx).load(dataSnapshot.getValue().toString()).into(holder.pro);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



            databaseReference.child("Profiles").child("Users").child(RevList.get(position).getSender()).child("username").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
holder.usernamec.setText(dataSnapshot.getValue().toString());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });



        }
        else {


            holder.pro.setVisibility(View.INVISIBLE);
            Glide.with(ctx).load(RevList.get(position).getMeme()).into(holder.blurred);

        }




holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        Intent intent = new Intent(ctx, FullScreenStroies.class);
        intent.putExtra("imgurl", RevList.get(position).getMeme());
intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        RevList.remove(position);
        notifyItemRemoved(position);
        ctx.startActivity(intent);

        DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference();
        databaseReference.child("PrivateMessages").child(MainActivity.modelInfo.getUid()).child("Recived").child(keyLIST.get(position)).child("seen").setValue(1);



    }
});


    }

    @Override
    public int getItemCount() {
        return RevList.size() ;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

         CircleImageView pro;
         ImageView blurred;
         ConstraintLayout constraintLayout;
TextView usernamec;

        public MyViewHolder(View itemView) {
            super(itemView);
pro=itemView.findViewById(R.id.propic);
blurred=itemView.findViewById(R.id.blurredimagview);
constraintLayout=itemView.findViewById(R.id.c);
usernamec=itemView.findViewById(R.id.usernamec);



        }
    }




}
