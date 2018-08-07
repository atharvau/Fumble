package com.example.atharva.memeapp;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

public class FullScreenMeme extends AppCompatActivity {
ImageView fullscreen;
ImageButton save;
String jk;
Bitmap hBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_meme);
        final PhotoView photoView = (PhotoView) findViewById(R.id.fullscreen);

Intent intent=getIntent();
String url=intent.getStringExtra("imgurl");

     DatabaseReference  mdb= FirebaseDatabase.getInstance().getReference();
        mdb.child("Discover").child("posts").child(url).child("meme").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    Glide.with(FullScreenMeme.this).load(dataSnapshot.getValue().toString()).into(photoView);
           jk=dataSnapshot.getValue().toString();

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Picasso.get().load(jk).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Log.d("A", "onBitmapLoaded: ");

                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                String path = MediaStore.Images.Media.insertImage(getContentResolver(),
                        bitmap, "Title", null);
                Uri imageUri =  Uri.parse(path);
                share.putExtra(Intent.EXTRA_STREAM, imageUri);
                startActivity(Intent.createChooser(share, "Select"));            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });
    }
});



    }


    private String saveImage(Bitmap image) {
        String savedImagePath = null;

        String imageFileName = "JPEG_" + "atharva" + ".jpg";
        File storageDir = new File(            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                + "/DCIM");
        boolean success = true;
        if (!storageDir.exists()) {
            success = storageDir.mkdirs();
        }
        if (success) {
            File imageFile = new File(storageDir, imageFileName);
            savedImagePath = imageFile.getAbsolutePath();
            try {
                OutputStream fOut = new FileOutputStream(imageFile);
                image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                fOut.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Add the image to the system gallery
            galleryAddPic(savedImagePath);
            Toast.makeText(getBaseContext(), "IMAGE SAVED", Toast.LENGTH_LONG).show();
        }
        return savedImagePath;
    }


    private void galleryAddPic(String imagePath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(imagePath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        sendBroadcast(mediaScanIntent);
    }

}
