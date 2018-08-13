package com.example.atharva.memeapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.ArrayList;

public class NewMemePost extends AppCompatActivity {
    private static final int SELECT_PHOTO = 100;
    public static Uri selectedImage;
public static ImageView imageView;
public static EditText Caption;
DatabaseReference databaseReference;
public static ModelInfo modelInfo;

public static int Points;
public static EditText searchtext;
public  static ImageButton search;


//////////////////////////////////////////////////////////////////////////////////
FirebaseStorage storage;
    StorageReference storageRef,imageRef,DownRef;
    ProgressDialog progressDialog;
    UploadTask uploadTask;
    public Uri down;
    public static String username="athrva";
    DatabaseReference fb;
    ///////////////////////////////////////////////////////////////////////////////////////////////
    String key;
String Scaption;
EditText caption;
////////////////////////////////////
DatabaseReference   UserDatabase;
    ArrayList<String> flist = new ArrayList<String>();

    ArrayList<String> Slist = new ArrayList<String>();


    SearchListadapter hj;
    public static  SearchListBase searchListBase;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_meme_post);

   selectedImage= MainActivity.resultUri;
   modelInfo=MainActivity.modelInfo;
   Toast.makeText(getBaseContext(),""+selectedImage,Toast.LENGTH_SHORT).show();
   setTitle("New Post");

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        Loadfriendlist();




        //------------------------------------------------------------//
        final ListView listView=findViewById(R.id.list);
        searchListBase=new SearchListBase(this,Slist);
        listView.setAdapter(searchListBase);
        UserDatabase = FirebaseDatabase.getInstance().getReference("Profiles").child("Users");







///////////////////////////////////////////////////////////////
        search=findViewById(R.id.search);
        searchtext=findViewById(R.id.searchtext);


searchtext.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        findViewById(R.id.recyclerview).setVisibility(View.GONE);

        listView.setVisibility(View.VISIBLE);

    }
});



search.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {


        Slist.clear();
String searchText=searchtext.getText().toString();
        Query firebaseSearchQuery = UserDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");



        firebaseSearchQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Toast.makeText(getBaseContext(),dataSnapshot.getKey().toString(),Toast.LENGTH_SHORT).show();
Slist.add(dataSnapshot.getKey().toString()) ;searchListBase=new SearchListBase(NewMemePost.this,Slist);
                listView.setAdapter(searchListBase);



            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
});




        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), 2);
        viewPager.setAdapter(adapter);

     TabLayout   tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

tabLayout.getTabAt(0).setText("Recent");
tabLayout.getTabAt(1).setText("Search");












        storage=FirebaseStorage.getInstance();
        storageRef=FirebaseStorage.getInstance().getReference();

key=MainActivity.key;
caption=findViewById(R.id.caption);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
imageView=findViewById(R.id.imageView4);
Glide.with(this).load(selectedImage).into(imageView);
databaseReference= FirebaseDatabase.getInstance().getReference();
databaseReference.child("A").setValue("A");


    }
    public void selectImage(View view) {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri resultUri = result.getUri();

                Glide.with(this).load(resultUri).into(imageView);

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu1, menu);
        return super.onCreateOptionsMenu(menu);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.share:
                uploadImage();

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void uploadImage() {

        //create reference to images folder and assing a name to the file that will be uploaded

        imageRef = storageRef.child("images/" + "Posts" + "/" + "Users"+"/"+modelInfo.getUid()+"/"+key);


        progressDialog = new ProgressDialog(this);
        progressDialog.setMax(100);
        progressDialog.setMessage("Uploading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.show();
        progressDialog.setCancelable(false);
        //starting upload
        uploadTask = imageRef.putFile(selectedImage);
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
                Toast.makeText(getBaseContext(),"Error in uploading!",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                // taskSnapshot.getMetadata() contains file metadata such as size, content-type, and download URL.
                //   down = taskSnapshot.getDownloadUrl();


                Toast.makeText(getBaseContext(),"Upload successful",Toast.LENGTH_SHORT).show();


                progressDialog.dismiss();
                DownRef = storageRef.child("images/" + "Posts" + "/" + "Users"+"/"+modelInfo.getUid()+"/"+key+"/");






                DownRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {



                        String abix=uri.toString();
Toast.makeText(getBaseContext(),abix,Toast.LENGTH_SHORT).show();

                        Scaption=caption.getText().toString();
//////////////////////////////////////////////////////////////////////////////////////////////////
                        databaseReference.child("Discover").child("posts").child(key).setValue(modelInfo);
                        databaseReference.child("Discover").child("posts").child(key).child("caption").setValue(Scaption);
                        databaseReference.child("Discover").child("posts").child(key).child("like").setValue(0);
                        databaseReference.child("Discover").child("list").child(key).setValue(key);


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                        databaseReference.child("Userposts").child(modelInfo.getUid()).child("posts").child(key).child("meme").setValue(abix);
                        databaseReference.child("Userposts").child(modelInfo.getUid()).child("posts").child(key).setValue(modelInfo);
                        databaseReference.child("Userposts").child(modelInfo.getUid()).child("posts").child(key).child("caption").setValue(Scaption);
                        databaseReference.child("Userposts").child(modelInfo.getUid()).child("posts").child(key).child("like").setValue(0);
                        databaseReference.child("Userposts").child(modelInfo.getUid()).child("list").child(key).setValue(key);

                        databaseReference.child("Discover").child("posts").child(key).child("meme").setValue(abix);





                    }
                });
                }
        });

        }



        public  void Loadfriendlist(){
        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Discover").child("Friendlist").child(modelInfo.getUid()).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.d("A", "onChildAdded: "+dataSnapshot.getValue().toString());
                flist.add(dataSnapshot.getValue().toString());

Toast.makeText(getBaseContext(),dataSnapshot.getValue().toString(),Toast.LENGTH_SHORT).show();

                LinearLayoutManager linearLayoutManager=new LinearLayoutManager(NewMemePost.this);
                RecyclerView mMessageList= (RecyclerView) findViewById(R.id.recyclerview);

                mMessageList.setHasFixedSize(true);
                mMessageList.setLayoutManager(linearLayoutManager);
                flistadapter    myAdapter=new flistadapter(flist,NewMemePost.this);
                mMessageList.setAdapter(myAdapter);
                myAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        }}






