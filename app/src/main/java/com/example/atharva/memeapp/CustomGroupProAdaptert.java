package com.example.atharva.memeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CustomGroupProAdaptert extends android.widget.BaseAdapter {

    Context ctx;
    ArrayList<String> RevList = new ArrayList<String>();

    public CustomGroupProAdaptert(Context ctx, ArrayList<String> revList) {
        this.ctx = ctx;
        this.RevList = revList;

    }

    @Override
    public int getCount() {
        return RevList.size();
    }

    @Override
    public Object getItem(int i) {
        return RevList.size();
    }

    @Override
    public long getItemId(int i) {
        return RevList.size();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View view1=view;

        if (view==null){

            DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

            view1 = LayoutInflater.from(ctx).inflate(R.layout.groupprof, viewGroup, false);


            TextView username=view1.findViewById(R.id.usernamme);


            username.setText(RevList.get(i));


            ImageView imageView10=view1.findViewById(R.id.imageView10);
            imageView10.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    DatabaseReference databaseReference1=FirebaseDatabase.getInstance().getReference();
                    




                }
            });
















        }


;



        return view1;
    }

    @Override
    public void notifyDataSetChanged() {

        super.notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }
    public void update( ArrayList<ModelMessage> mRevList ){


        notifyDataSetChanged();

    }

    @Override
    public void notifyDataSetInvalidated() {
        super.notifyDataSetInvalidated();
    }
}
