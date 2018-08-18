package com.example.atharva.memeapp;

import android.app.ActionBar;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final List<String> messagesList=new ArrayList<String>();
    private LinearLayoutManager linearLayoutManager;
    private MyAdapter myAdapter;
    public static Uri resultUri;
    public static ModelInfo modelInfo;
    String uid;
//////////////////////////////////////////////////////////////////
    public static String key;
////////////////////////////////////////////////////////////////////////////

    Modelmemepost modelmemepost;


    DatabaseReference mdb;
    public static ArrayList<Modelmemepost> Post = new ArrayList<Modelmemepost>();
    public static ArrayList<Modelmemepost> REVPost = new ArrayList<Modelmemepost>();
       public static ArrayList<String> List = new ArrayList<String>();
    public static ArrayList<String> REVList = new ArrayList<String>();

    public static ArrayList<String> uidlist = new ArrayList<String>();
    public static ArrayList<String> RevUidList = new ArrayList<String>();






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
uid=FirebaseAuth.getInstance().getUid();
        FirebaseInstanceId.getInstance().getToken();
Toast.makeText(getBaseContext(),FirebaseInstanceId.getInstance().getToken().toString(),Toast.LENGTH_SHORT).show();
        getList();
//////////////////////////////////////////////
        getInfo();

        GetKey();
      //  getMemes();
        SendNotif();
        SETREF();
        //////SendNotif////////////////////////////////////


        Delete(0);

        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Topposts.class);
                startActivity(intent);



            }
        });

findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

       CropImage.activity()
               .setGuidelines(CropImageView.Guidelines.ON)
               .start(MainActivity.this);

    }

});

findViewById(R.id.button7).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {



        Intent intent=new Intent(MainActivity.this,Story.class);


        startActivity(intent);





    }
});






findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(MainActivity.this,Stories.class);
        startActivity(intent);
    }
});



findViewById(R.id.mess).setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(MainActivity.this,Messages.class);
        startActivity(intent);

    }
});




    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                resultUri = result.getUri();
                Intent intent=new Intent(MainActivity.this,NewMemePost.class);
              startActivity(intent);

             //   Glide.with(this).load(resultUri).into(imageView);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }

}




public void getInfo(){

    modelInfo=new ModelInfo();
    DatabaseReference md= FirebaseDatabase.getInstance().getReference();
    md.child("Profiles").child("Users").child(uid).child("name").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            String a=dataSnapshot.getValue().toString();
            modelInfo.setName(a);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    md.child("Profiles").child("Users").child(uid).child("username").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            String a=dataSnapshot.getValue().toString();
            modelInfo.setUsername(a);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });
    md.child("Profiles").child("Users").child(uid).child("profilepicture").addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            String a=dataSnapshot.getValue().toString();
            modelInfo.setProfilepic(a);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });

    modelInfo.setUid(uid);





}
public void GetKey(){

        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
       key= databaseReference.child("Discover").child("Posts").push().getKey();

}
public void getMemes(){

    Post.clear();


        mdb=FirebaseDatabase.getInstance().getReference();
        mdb.child("Discover").child("posts").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Modelmemepost modelmemepost=new Modelmemepost();
if(dataSnapshot.child("username").exists()) modelmemepost.setUsername(dataSnapshot.child("username").getValue().toString());
if(dataSnapshot.child("name").exists()) modelmemepost.setName(dataSnapshot.child("name").getValue().toString());
if(dataSnapshot.child("meme").exists()) modelmemepost.setMeme(dataSnapshot.child("meme").getValue().toString());
if(dataSnapshot.child("uid").exists()) modelmemepost.setUid(dataSnapshot.child("uid").getValue().toString());
if(dataSnapshot.child("caption").exists()) modelmemepost.setCaption(dataSnapshot.child("caption").getValue().toString());
//if(dataSnapshot.child("like").exists()) modelmemepost.setLike(Integer.parseInt(dataSnapshot.getValue().toString()));
if(dataSnapshot.child("profilepicture").exists()) modelmemepost.setProfilepicture(dataSnapshot.child("profilepicture").getValue().toString());
modelmemepost.setLike(0);
                Log.d("A", "onChildAdded: "+modelmemepost.getMeme());
Post.add(modelmemepost);

                REVPost.clear();

                int size2 = Post.size() - 1;
                for (int i = size2; i >= 0; i--) {
                    REVPost.add(Post.get(i));

                }



                linearLayoutManager=new LinearLayoutManager(MainActivity.this);
                RecyclerView mMessageList= (RecyclerView) findViewById(R.id.recyclerview);

                mMessageList.setHasFixedSize(true);
                mMessageList.setLayoutManager(linearLayoutManager);
            //    myAdapter=new MyAdapter(REVPost,MainActivity.this);
             //   mMessageList.setAdapter(myAdapter);






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

public  void getList(){
      List.clear();

        mdb=FirebaseDatabase.getInstance().getReference();
        mdb.child("Discover").child("list").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                REVList.clear();

                String a=dataSnapshot.getValue().toString();
                List.add(a);


                int size2 = List.size() - 1;
                for (int i = size2; i >= 0; i--) {
                    REVList.add(List.get(i));

                }



                linearLayoutManager=new LinearLayoutManager(MainActivity.this);
                RecyclerView mMessageList= (RecyclerView) findViewById(R.id.recyclerview);

                mMessageList.setHasFixedSize(true);
                mMessageList.setLayoutManager(linearLayoutManager);
                myAdapter=new MyAdapter(REVList,MainActivity.this);
                mMessageList.setAdapter(myAdapter);
myAdapter.notifyDataSetChanged();
                Log.d("A", REVList.size()+"");

                if(dataSnapshot.getChildrenCount()==0){
                }



            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                REVList.clear();

                String a=dataSnapshot.getValue().toString();
                List.add(a);


                int size2 = List.size() - 1;
                for (int i = size2; i >= 0; i--) {
                    REVList.add(List.get(i));

                }



                linearLayoutManager=new LinearLayoutManager(MainActivity.this);
                RecyclerView mMessageList= (RecyclerView) findViewById(R.id.recyclerview);

                mMessageList.setHasFixedSize(true);
                mMessageList.setLayoutManager(linearLayoutManager);
                myAdapter=new MyAdapter(REVList,MainActivity.this);
                mMessageList.setAdapter(myAdapter);

                Log.d("A", REVList.size()+"");
                myAdapter.notifyDataSetChanged();
                if(dataSnapshot.getChildrenCount()==0){
                    Toast.makeText(getBaseContext(),"A",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                REVList.clear();

                String a=dataSnapshot.getValue().toString();
                List.add(a);


                int size2 = List.size() - 1;
                for (int i = size2; i >= 0; i--) {
                    REVList.add(List.get(i));

                }



                linearLayoutManager=new LinearLayoutManager(MainActivity.this);
                RecyclerView mMessageList= (RecyclerView) findViewById(R.id.recyclerview);

                mMessageList.setHasFixedSize(true);
                mMessageList.setLayoutManager(linearLayoutManager);
                myAdapter=new MyAdapter(REVList,MainActivity.this);
                mMessageList.setAdapter(myAdapter);

                Log.d("A", REVList.size()+"");
                myAdapter.notifyDataSetChanged();
                if(dataSnapshot.getChildrenCount()==0){
                    Toast.makeText(getBaseContext(),"A",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


}
    public void  Delete(int position){


        final DatabaseReference databaseReference =FirebaseDatabase.getInstance().getReference();
        databaseReference.child("Discover").child("like").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
               long h=dataSnapshot.getChildrenCount();
               databaseReference.child("Discover").child("posts").child(dataSnapshot.getKey()).child("like").setValue(h);
                databaseReference.child("Discover").child("likecountposts").child(dataSnapshot.getKey()).setValue(h);


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                long h=dataSnapshot.getChildrenCount();
                databaseReference.child("Discover").child("posts").child(dataSnapshot.getKey()).child("like").setValue(h);
                databaseReference.child("Discover").child("likecountposts").child(dataSnapshot.getKey()).setValue(h);

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                long h=dataSnapshot.getChildrenCount();
                databaseReference.child("Discover").child("posts").child(dataSnapshot.getKey()).child("like").setValue(h);
                databaseReference.child("Discover").child("likecountposts").child(dataSnapshot.getKey()).setValue(h);
                databaseReference.child("Discover").child("likecountposts").child(dataSnapshot.getKey()).setValue(h);

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                long h=dataSnapshot.getChildrenCount();
                databaseReference.child("Discover").child("posts").child(dataSnapshot.getKey()).child("like").setValue(h);
                databaseReference.child("Discover").child("likecountposts").child(dataSnapshot.getKey()).setValue(h);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    public  void SendNotif(){


        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
        String key=  databaseReference.child("Notifications").child(MainActivity.modelInfo.getUid()).push().getKey();
        databaseReference.child("Notifications").child(MainActivity.modelInfo.getUid()).child(key).child("from").setValue("a");
        databaseReference.child("Notifications").child(MainActivity.modelInfo.getUid()).child(key).child("type").setValue("request");

    }
    public void SETREF(){

        DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference();
String token=FirebaseInstanceId.getInstance().getToken();
        databaseReference.child("Profiles").child("Users").child(MainActivity.modelInfo.getUid()).child("device_token").setValue(token);

    }
}
