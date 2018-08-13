package com.example.atharva.memeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class try1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_try1);
        // Parent layout
        final ScrollView scroll=findViewById(R.id.sc);

        ViewGroup main = (ViewGroup) findViewById(R.id.lin);

for (int i=0;i<101;i++){

        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.leftmessage, null);
        main.addView(view, i);
        view.requestFocus();


}
        scroll.post(new Runnable() {
            @Override
            public void run() {
                scroll.fullScroll(View.FOCUS_DOWN);
            }
        });

    }

}
