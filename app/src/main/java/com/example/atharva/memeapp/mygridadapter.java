package com.example.atharva.memeapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class mygridadapter extends BaseAdapter {
    Context ctx;
    ArrayList<String> RevList = new ArrayList<String>();

    String imgurl;

    public mygridadapter(Context ctx,ArrayList<String> RevList) {
        this.ctx = ctx;
        this.RevList=RevList;

    }

    @Override
    public int getCount() {
        return RevList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
       final int pos;
       pos=i;

        View grid;
        LayoutInflater inflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {
            grid = new View(ctx);
            grid = inflater.inflate(R.layout.gridviewimage, null);
            final ImageView img=grid.findViewById(R.id.imageView5);
                final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("Discover").child("posts").child(RevList.get(i)).child("meme").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        Glide.with(ctx).load(dataSnapshot.getValue().toString()).into(img);
                        Toast.makeText(ctx, "AA", Toast.LENGTH_SHORT).show();
imgurl=dataSnapshot.getValue().toString();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



                grid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {


                        Intent ias1=new Intent(ctx,FullScreenMeme.class);


                        //Pair<View, String> p1 = Pair.create((View)holder.img, "img");
                        ias1.putExtra("imgurl",RevList.get(pos));

                        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)ctx);
                        ctx.startActivity(ias1, options.toBundle());

                    }
                });



        } else {
            grid = (View) convertView;
        }

        return grid;
    }



    }



