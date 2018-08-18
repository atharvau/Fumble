package com.example.atharva.memeapp;


import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class flistadapter extends RecyclerView.Adapter<flistadapter.MyViewHolder>{
    Context ctx;

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

    ArrayList<String> RevList = new ArrayList<String>();
    public flistadapter(  ArrayList<String> RevList, Context ctx)
    {
        this.RevList =RevList ;
        this.ctx=ctx;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.leftmessage,parent,false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        final DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Profiles").child("Users").child(RevList.get(position)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

if (dataSnapshot.child("profilepicture").exists()){


            Glide.with(ctx).load(dataSnapshot.child("profilepicture").getValue().toString()).into(holder.imageView);

                }


                if (dataSnapshot.child("username").exists()){
holder.textView.setText(dataSnapshot.child("username").getValue().toString());
}




                if (dataSnapshot.child("name").exists()){
                    holder.name.setText(dataSnapshot.child("name").getValue().toString());
                }







            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



holder.send.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        if(abix==null) {
            int j;
            j= uploadImage(position);

            if(j==1){



               holder.send.setVisibility(View.GONE);
                holder.lottieAnimationView.setVisibility(View.VISIBLE);


            }
        }

        else{


            int kj = D(position);

            if(kj==1){



                holder.send.setVisibility(View.GONE);
                holder.lottieAnimationView.setVisibility(View.VISIBLE);


            }
        }


    }
});



    }

    @Override
    public int getItemCount() {
        return RevList.size() ;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

ImageView imageView;
TextView textView;

ImageView send;
TextView name;

LottieAnimationView lottieAnimationView;


        public MyViewHolder(View itemView) {
            super(itemView);

             imageView=itemView.findViewById(R.id.imageView);
             textView=itemView.findViewById(R.id.usernamme);
send=itemView.findViewById(R.id.imageView10);
name=itemView.findViewById(R.id.name);
lottieAnimationView=itemView.findViewById(R.id.animation_view);




        }
    }



    public int  D(final int position) {


        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        String key = databaseReference.child("PrivateMessages").child(RevList.get(position)).push().getKey();

        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();


        MemeSenderTime memeSenderTime=new MemeSenderTime();

        memeSenderTime.setMeme(abix);
        memeSenderTime.setTime(ts);
        memeSenderTime.setSender(MainActivity.modelInfo.getUid());
        memeSenderTime.setSeen(0);
        ModelStory modelStory=new ModelStory();
        modelStory.setMeme(abix);
        modelStory.setSeen(0);
        modelStory.setSender(MainActivity.modelInfo.getUid());
        modelStory.setProfilepicture(MainActivity.modelInfo.getProfilepic());


        databaseReference.child("PrivateMessages").child(RevList.get(position)).child("Recived").child(key).setValue(modelStory);



        databaseReference.child("Messages").child(MainActivity.modelInfo.getUid()).child(RevList.get(position)).child(key).setValue(memeSenderTime);
        databaseReference.child("Messages").child(RevList.get(position)).child(MainActivity.modelInfo.getUid()).child(key).setValue(memeSenderTime);



        databaseReference.child("PrivateMessages").child(RevList.get(position)).child("List").child(MainActivity.modelInfo.getUid()).setValue(ts);
        databaseReference.child("PrivateMessages").child(MainActivity.modelInfo.getUid()).child("List").child(RevList.get(position)).setValue(ts);







        return 1;







    }





    public int uploadImage(final int position) {

        //create reference to images folder and assing a name to the file that will be uploaded




        storage= FirebaseStorage.getInstance();
        storageRef=FirebaseStorage.getInstance().getReference();

        DatabaseReference database=FirebaseDatabase.getInstance().getReference();
        final String key=database.child("Discover").child("posts").push().getKey();
        imageRef = storageRef.child("images/" + "Posts" + "/" +"Private"+"/"+MainActivity.modelInfo.getUid()+"/"+key);


        progressDialog = new ProgressDialog(ctx);
        progressDialog.setMax(100);
        progressDialog.setMessage("Uploading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        progressDialog.setCancelable(false);
        //starting upload
        uploadTask = imageRef.putFile(MainActivity.resultUri);
        // Observe state change events such as progress, pause, and resume
        uploadTask.addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                //sets and increments value of progressbar
                progressDialog.incrementProgressBy((int) progress);
            }
        });
        // Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
                Toast.makeText(ctx,"Error in uploading!",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                //   down = taskSnapshot.getDownloadUrl();


                Toast.makeText(ctx,"Upload successful",Toast.LENGTH_SHORT).show();


                progressDialog.dismiss();
                DownRef = storageRef.child("images/" + "Posts" + "/" +"Private"+"/"+MainActivity.modelInfo.getUid()+"/"+key+"/");






                DownRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {



                        abix=uri.toString();
                        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                        String key = databaseReference.child("PrivateMessages").child(RevList.get(position)).push().getKey();

                        Long tsLong = System.currentTimeMillis()/1000;
                        String ts = tsLong.toString();


                        MemeSenderTime memeSenderTime=new MemeSenderTime();


                        memeSenderTime.setMeme(abix);
                        memeSenderTime.setTime(ts);
                        memeSenderTime.setSender(MainActivity.modelInfo.getUid());
                        memeSenderTime.setSeen(0);
                        ModelStory modelStory=new ModelStory();
                        modelStory.setMeme(abix);
                        modelStory.setSeen(0);
                        modelStory.setSender(MainActivity.modelInfo.getUid());
                        modelStory.setProfilepicture(MainActivity.modelInfo.getProfilepic());


                        databaseReference.child("PrivateMessages").child(RevList.get(position)).child("Recived").child(key).setValue(modelStory);



                        databaseReference.child("Messages").child(MainActivity.modelInfo.getUid()).child(RevList.get(position)).child(key).setValue(memeSenderTime);
                        databaseReference.child("Messages").child(RevList.get(position)).child(MainActivity.modelInfo.getUid()).child(key).setValue(memeSenderTime);



                        databaseReference.child("PrivateMessages").child(RevList.get(position)).child("List").child(MainActivity.modelInfo.getUid()).setValue(ts);
                        databaseReference.child("PrivateMessages").child(MainActivity.modelInfo.getUid()).child("List").child(RevList.get(position)).setValue(ts);





                    }
                });
            }
        });


        return 1;
    }


}
