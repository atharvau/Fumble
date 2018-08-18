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

public class SearchListBase extends android.widget.BaseAdapter {
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    Context ctx;
    ArrayList<String> RevList = new ArrayList<String>();


LottieAnimationView lottieAnimationView;

ImageView imageView;
    //////////////////////////////////////////////////////////////////////////////////
    FirebaseStorage storage;
    StorageReference storageRef,imageRef,DownRef;
    ProgressDialog progressDialog;
    UploadTask uploadTask;
    public Uri down;
    public static String username="athrva";
    DatabaseReference fb;
    /////////////////////////////
String abix;

    public SearchListBase(Context ctx, ArrayList<String> revList) {
        this.ctx = ctx;
        this.RevList = revList;

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

            DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference();

            view1 = LayoutInflater.from(ctx).inflate(R.layout.leftmessage, viewGroup, false);

            ///////////////////////////////////
            lottieAnimationView=view1.findViewById(R.id.animation_view);

imageView=view1.findViewById(R.id.imageView10);
            final TextView textView=view1.findViewById(R.id.usernamme);
            final TextView name=view1.findViewById(R.id.name);
            final ImageView imageView=view1.findViewById(R.id.imageView);


            databaseReference.child("Profiles").child("Users").child(RevList.get(i)).child("username").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


               textView.setText(dataSnapshot.getValue().toString());


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

            databaseReference.child("Profiles").child("Users").child(RevList.get(i)).child("name").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    name.setText(dataSnapshot.getValue().toString());


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



        }


        final View finalView = view1;
        imageView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        if(abix==null) {
         int j;
           j= uploadImage(i);

           if(j==1){



               finalView.findViewById(R.id.imageView10).setVisibility(View.GONE);
               finalView.findViewById(R.id.animation_view).setVisibility(View.VISIBLE);


           }
        }

        else{


     int kj = D(i);

            if(kj==1){



                finalView.findViewById(R.id.imageView10).setVisibility(View.GONE);
                finalView.findViewById(R.id.animation_view).setVisibility(View.VISIBLE);


            }
        }


    }
});





        return view1;
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
