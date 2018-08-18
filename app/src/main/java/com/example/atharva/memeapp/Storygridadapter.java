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
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Storygridadapter extends BaseAdapter {
    Context ctx;
    ArrayList<ModelStories> RevList = new ArrayList<ModelStories>();
    ArrayList<String> keyList = new ArrayList<String>();


    public Storygridadapter(Context ctx, ArrayList<ModelStories> RevList,    ArrayList<String> keyList) {
        this.ctx = ctx;
        this.RevList=RevList;
this.keyList=keyList;

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
            grid = inflater.inflate(R.layout.messagecard, null);
            final ImageView pro=grid.findViewById(R.id.propic);
            final ImageView blurred=grid.findViewById(R.id.blurredimagview);



grid.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {




        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
        databaseReference.child("PrivateMessages").child(MainActivity.modelInfo.getUid()).child(keyList.get(pos)).child("seen").setValue(1);
Intent intent=new Intent(ctx,FullScreenStroies.class);
intent.putExtra("uid",keyList.get(pos));
        intent.putExtra("meme",RevList.get(pos).getMeme());
        intent.putExtra("profilepicture",RevList.get(pos).getProfilepicture());
        ctx.startActivity(intent);

    }
});



            final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

if(RevList.get(pos).getSeen()==0) {


    Picasso.get().load(RevList.get(pos).getProfilepicture()).into(pro);


    Picasso.get().load(RevList.get(pos).getProfilepicture()).transform(new jp.wasabeef.picasso.transformations.BlurTransformation(ctx, 1, 80)).into(blurred);


}
else {


    pro.setVisibility(View.INVISIBLE);
Glide.with(ctx).load(RevList.get(pos).getMeme()).into(blurred);

}









        } else {
            grid = (View) convertView;
        }

        return grid;
    }



    }



