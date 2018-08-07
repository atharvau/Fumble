package com.example.atharva.memeapp;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.varunest.sparkbutton.SparkButton;
import com.wajahatkarim3.clapfab.ClapFAB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Atharva on 1/16/2018.
 */


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>  {
Context ctx;


int FinInt;
public static  ModelInfo modelInfo;
int iLike=0,i;




    ArrayList<String> RevList = new ArrayList<String>();
    public MyAdapter(  ArrayList<String> RevList, Context ctx)
    {
        this.RevList =RevList ;
this.ctx=ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listcard,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        ////////////////////////////////////////////////////////////////////
modelInfo=MainActivity.modelInfo;

DatabaseReference mdb;

        mdb= FirebaseDatabase.getInstance().getReference();
        mdb.child("Discover").child("posts").child(RevList.get(position)).child("meme").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

       if (dataSnapshot.exists()) Glide.with(ctx).load(dataSnapshot.getValue().toString()).into(holder.img);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//-----------------------------------------------------------------------------------------------------------------//
        mdb.child("Discover").child("posts").child(RevList.get(position)).child("profilepicture").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) Glide.with(ctx).load(dataSnapshot.getValue().toString()).into(holder.pro);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mdb.child("Discover").child("posts").child(RevList.get(position)).child("caption").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) holder.caption.setText(dataSnapshot.getValue().toString());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        mdb.child("Discover").child("posts").child(RevList.get(position)).child("caption").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) holder.username.setText(dataSnapshot.getValue().toString());


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });













holder.like.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
iLike++;



        if(iLike%2!=0) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child("Discover").child("like").child(RevList.get(position)).child(modelInfo.getUsername()).setValue(modelInfo.getUid());
       holder.like.playAnimation();
       holder.like.setChecked(true);
        }
else
        if(iLike%2==0) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child("Discover").child("like").child(RevList.get(position)).child(modelInfo.getUsername()).removeValue();
            holder.like.playAnimation();
            holder.like.setChecked(false);
        }









    }
});



////////////////////////////////////////////////////////////////////////////////
holder.img.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent ias=new Intent(ctx,FullScreenMeme.class);


        Pair<View, String> p1 = Pair.create((View)holder.img, "img");
       ias.putExtra("imgurl",RevList.get(position));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)ctx, p1);
       ctx.startActivity(ias, options.toBundle());
    }
});


///////////////////////////////////////////////////////////////////////////////////////////////////////

                                   mdb.child("Discover").child("like").child(RevList.get(position)).addValueEventListener(new ValueEventListener() {
                                       @Override
                                       public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                                           Log.d("A", "onDataChange:"+dataSnapshot.getChildrenCount()+"");

                                           holder.counts.setText(dataSnapshot.getChildrenCount()+"");
                                           
                                       }

                                       @Override
                                       public void onCancelled(@NonNull DatabaseError databaseError) {

                                       }
                                   });
        
        

        
        


        

        
        
        
        

        

        
        

        

        
        

        

        
        

        
        

















































    }

    @Override
    public int getItemCount() {
        return RevList.size() ;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView username,caption;
        ImageView img;
        ImageView pro;
        SparkButton like;
        TextView counts;

        public MyViewHolder(View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.username);
            caption=itemView.findViewById(R.id.caption);

            img=itemView.findViewById(R.id.img);
            pro=itemView.findViewById(R.id.imageView);
            like=itemView.findViewById(R.id.likeButton);
            counts=itemView.findViewById(R.id.textView5);


        }
    }



    public  void  LikeClick(int position){





    }


}
