package com.example.atharva.memeapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import de.hdodenhof.circleimageview.CircleImageView;

public class ListAdapter extends android.widget.BaseAdapter {

    ArrayList<ModelMess> RevList = new ArrayList<ModelMess>();
    ArrayList<String> Strew=new  ArrayList<String>();
    Context ctx;
String uid;

    public ListAdapter(ArrayList<ModelMess> revList, ArrayList<String> strew, Context ctx, String uid) {
        RevList = revList;
        Strew = strew;
        this.ctx = ctx;
        this.uid = uid;
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
    public View getView(int position, View view, ViewGroup viewGroup) {
        View view1=view;

        if (view==null) {

            view1 = LayoutInflater.from(ctx).inflate(R.layout.chatuimessage, viewGroup, false);


            LinearLayout l1;
            LinearLayout r1;
            ImageView lmeme, rmeme;
            final CircleImageView cl1, cr1;
            ImageView seen;


            TextView time;


            ImageView overlay;
            LinearLayout lin;


            l1 = view1.findViewById(R.id.l1);
            r1 = view1.findViewById(R.id.r1);
            lmeme = view1.findViewById(R.id.lt);
            rmeme = view1.findViewById(R.id.rt);
            cl1 = view1.findViewById(R.id.li);
            cr1 = view1.findViewById(R.id.ri);
            seen = view1.findViewById(R.id.seen);
            time = view1.findViewById(R.id.seentime);
            overlay = view1.findViewById(R.id.imj);
            lin = view1.findViewById(R.id.lin);


            String a = RevList.get(position).getSender();


            if (a.equals(MainActivity.modelInfo.getUid())) {
                l1.setVisibility(View.GONE);

                Glide.with(ctx).load(RevList.get(position).getMeme()).into(rmeme);
                if (RevList.get(position).getSeen() == 1) seen.setVisibility(View.VISIBLE);

                long an = Long.parseLong(RevList.get(position).getTime());
                Calendar calendar = Calendar.getInstance();
                TimeZone tz = TimeZone.getDefault();
                calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.getTimeInMillis()));
                SimpleDateFormat sdf = new SimpleDateFormat(" HH:mm", Locale.getDefault());
                java.util.Date currenTimeZone = new java.util.Date((long) an * 1000);
                time.setText(sdf.format(currenTimeZone));


                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("Profiles").child("Users").child(uid).child("profilepicture").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Glide.with(ctx).load(dataSnapshot.getValue().toString()).into(cr1);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            } else {


                r1.setVisibility(View.GONE);

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("Profiles").child("Users").child(uid).child("profilepicture").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                        Glide.with(ctx).load(dataSnapshot.getValue().toString()).into(cl1);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Glide.with(ctx).load(RevList.get(position).getMeme()).into(lmeme);

            }

            final int[] i = {0};


        }






        return view1;
    }



    @Override
    public int getItemViewType(int position) {

        return position;
    }
    @Override
    public int getViewTypeCount() {

        return getCount();
    }


    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
}
