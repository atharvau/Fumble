package com.example.atharva.memeapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SendThisMEME extends AppCompatActivity {
public  static String meme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_this_meme);


        Intent intent=getIntent();


    meme=intent.getStringExtra("meme");


       ImageView imageView4=findViewById(R.id.imageView4);

    Glide.with(this).load(meme).into(imageView4);



        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter2 adapter = new PagerAdapter2
                (getSupportFragmentManager(), 2);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("Recent");
        tabLayout.getTabAt(1).setText("Search");







        Toolbar mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);




















    }
}
