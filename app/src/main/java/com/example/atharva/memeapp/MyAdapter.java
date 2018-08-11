package com.example.atharva.memeapp;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
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

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Atharva on 1/16/2018.
 */


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
Context ctx;


int FinInt;
public static  ModelInfo modelInfo;
int iLike=0,i;


public static String memeoftheday;
public static String uid;


    ArrayList<String> RevList = new ArrayList<String>();
    public MyAdapter(  ArrayList<String> RevList, Context ctx)
    {
        this.RevList =RevList ;
this.ctx=ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listcard2,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        ////////////////////////////////////////////////////////////////////
modelInfo=MainActivity.modelInfo;

DatabaseReference mdb;
///////////////////////////////////////////////////////////
        MobileAds.initialize(ctx, " ca-app-pub-3940256099942544~3347511713");



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
        mdb.child("Discover").child("posts").child(RevList.get(position)).child("profilepic").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) Glide.with(ctx).load(dataSnapshot.getValue().toString()).into(holder.pro);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mdb.child("Discover").child("posts").child(RevList.get(position)).child("username").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) holder.username.setText(dataSnapshot.getValue().toString());


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



        mdb.child("Discover").child("posts").child(RevList.get(position)).child("uid").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) uid=dataSnapshot.getValue().toString();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        mdb.child("Discover").child("like").child(RevList.get(position)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                holder.counts.setText(dataSnapshot.getChildrenCount()+"");

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
            DatabaseReference mdb=FirebaseDatabase.getInstance().getReference();
            mdb.child("Discover").child("like").child(RevList.get(position)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    holder.counts.setText(dataSnapshot.getChildrenCount()+"");

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });






        }
else
        if(iLike%2==0) {
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
            databaseReference.child("Discover").child("like").child(RevList.get(position)).child(modelInfo.getUsername()).removeValue();
            holder.like.playAnimation();
            holder.like.setChecked(false);



       DatabaseReference mdb=FirebaseDatabase.getInstance().getReference();     mdb.child("Discover").child("like").child(RevList.get(position)).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    holder.counts.setText(dataSnapshot.getChildrenCount()+"");

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });




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





////////////////////////////////////////////////////////////////////////////////
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent ias1=new Intent(ctx,FullScreenMeme.class);


                Pair<View, String> p1 = Pair.create((View)holder.img, "img");
                ias1.putExtra("imgurl",RevList.get(position));

                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)ctx, p1);
                ctx.startActivity(ias1, options.toBundle());


            }
        });



        Delete(position);


      AdRequest adRequest = new AdRequest.Builder().build();
        holder.adView.loadAd(adRequest);

        
holder.dot.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        //pass the 'context' here
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(ctx);
        alertDialog.setTitle("Do You Want To Report This Post");
        //alertDialog.setMessage("");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
                builder.setTitle("Why You Want Report This Post")
                        .setItems(R.array.system, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // The 'which' argument contains the index position
                                // of the selected item

                                switch (which) {


                                    case 0:   DatabaseReference md = FirebaseDatabase.getInstance().getReference();
                                        md.child("Reports").child(RevList.get(position)).child(MainActivity.modelInfo.getUid()).setValue("NU");
                                        break;

                                    case 1:
                                        DatabaseReference md1 = FirebaseDatabase.getInstance().getReference();
                                        md1.child("Reports").child(RevList.get(position)).child(MainActivity.modelInfo.getUid()).setValue("RE");

                                    case 2:
                                        DatabaseReference md2 = FirebaseDatabase.getInstance().getReference();
                                        md2.child("Reports").child(RevList.get(position)).child(MainActivity.modelInfo.getUid()).setValue("YO");

                                    case 3:
                                        DatabaseReference md3 = FirebaseDatabase.getInstance().getReference();
                                        md3.child("Reports").child(RevList.get(position)).child(MainActivity.modelInfo.getUid()).setValue("OT");


                                }                            }
                        });


                AlertDialog alertDialog1=builder.create();
                alertDialog1.show();
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

        AlertDialog dialog = alertDialog.create();
        dialog.show();

    }
});
        
holder.textView5.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Intent ias1=new Intent(ctx,wholiked.class);


        Pair<View, String> p1 = Pair.create((View)holder.like, "like");
        ias1.putExtra("key",RevList.get(position));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)ctx, p1);
        ctx.startActivity(ias1, options.toBundle());


    }
});
        
holder.pro.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Intent intent=new Intent(ctx,Profile.class);


        Pair<View, String> p1 = Pair.create((View)holder.pro, "pro");
        Pair<View, String> p2 = Pair.create((View)holder.username, "username");

        intent.putExtra("uid",uid);

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)ctx, p1,p2);
        ctx.startActivity(intent, options.toBundle());










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
        CircleImageView pro;
        SparkButton like;
        TextView counts;
        ImageButton dot;
        TextView textView5;
        AdView adView;

        public MyViewHolder(View itemView) {
            super(itemView);
            username=itemView.findViewById(R.id.username);
            caption=itemView.findViewById(R.id.caption);

            img=itemView.findViewById(R.id.img);
            pro=itemView.findViewById(R.id.profile_image);
            like=itemView.findViewById(R.id.likeButton);
            counts=itemView.findViewById(R.id.textView5);
            dot=itemView.findViewById(R.id.dot2);
            textView5=itemView.findViewById(R.id.textView5);
            adView=itemView.findViewById(R.id.adView);



        }
    }



    public  void  LikeClick(int position){







    }
public void  Delete(final int position){


     DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
     databaseReference.child("Reports").child(RevList.get(position)).addValueEventListener(new ValueEventListener() {
         @Override
         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             Log.d("A", "Counts: "+dataSnapshot.getChildrenCount());
if(dataSnapshot.getChildrenCount()>5)
{

    DelePost(position);

}
         }

         @Override
         public void onCancelled(@NonNull DatabaseError databaseError) {

         }
     });



}

public void DelePost(int position){
    DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
    databaseReference.child("Discover").child("posts").child(RevList.get(position)).removeValue();

    databaseReference.child("Discover").child("list").child(RevList.get(position)).removeValue();
}




public void GetMEmeOft(){
    DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
    databaseReference.child("Discover").child("memeoftheday").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
         memeoftheday=dataSnapshot.getValue().toString();

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });




}

}
