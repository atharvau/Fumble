package com.example.atharva.memeapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class TabFragement2 extends Fragment {
View view;
    ///////////////////////////////////////////////////////////////////////////////////////////////
    String key;
    String Scaption;
    EditText caption;
    ////////////////////////////////////
    DatabaseReference UserDatabase;
    ArrayList<String> flist = new ArrayList<String>();

    ArrayList<String> Slist = new ArrayList<String>();

EditText searchtext;
    SearchListadapter hj;
    public static  SearchListBase searchListBase;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
view=inflater.inflate(R.layout.searching, container, false);





        //------------------------------------------------------------//
        final ListView listView=view.findViewById(R.id.list);
        searchListBase=new SearchListBase(getActivity(),Slist);
        listView.setAdapter(searchListBase);
        UserDatabase = FirebaseDatabase.getInstance().getReference("Profiles").child("Users");







///////////////////////////////////////////////////////////////
      ImageButton search=view.findViewById(R.id.search);
       searchtext=view.findViewById(R.id.searchtext);





       searchtext.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void afterTextChanged(Editable editable) {

               getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);


               Slist.clear();
               String searchText=editable.toString();

               Query firebaseSearchQuery = UserDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");



               firebaseSearchQuery.addChildEventListener(new ChildEventListener() {
                   @Override
                   public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {




                       if(!dataSnapshot.getKey().toString().equals(MainActivity.modelInfo.getUid())&&!Slist.contains(dataSnapshot.getKey().toString())) {
                           Toast.makeText(getActivity(), dataSnapshot.getKey().toString(), Toast.LENGTH_SHORT).show();
                           Slist.add(dataSnapshot.getKey().toString());
                           searchListBase = new SearchListBase(getActivity(), Slist);
                           listView.setAdapter(searchListBase);
                       }


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






        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);


                Slist.clear();
                String searchText=searchtext.getText().toString();
                Query firebaseSearchQuery = UserDatabase.orderByChild("name").startAt(searchText).endAt(searchText + "\uf8ff");



                firebaseSearchQuery.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                        Toast.makeText(getActivity(),dataSnapshot.getKey().toString(),Toast.LENGTH_SHORT).show();
                        Slist.add(dataSnapshot.getKey().toString()) ;searchListBase=new SearchListBase(getActivity(),Slist);
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









        return  view;
    }
}