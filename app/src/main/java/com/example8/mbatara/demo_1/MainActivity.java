package com.example8.mbatara.demo_1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity
{

    Button btn1,btn_view,btn_chart;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences("my_pref", Activity.MODE_PRIVATE);
        String  pref_user_id = sp.getString("pref_user_id", "user id null");
        String  pref_user_name = sp.getString("pref_user_name", "user name null");

        ImageView imageView = (ImageView) findViewById(R.id.img_1);
        Glide.with(this).load("https://ccms-uat.puninar.com/demo-load-image/"+pref_user_id).into(imageView);

        TextView txt_user_name = (TextView) findViewById(R.id.txt_user_name);
        txt_user_name.setText(pref_user_name);

        btn1 = (Button)findViewById(R.id.btn_add);
        btn_view = (Button)findViewById(R.id.btn_view);
        btn_chart = (Button)findViewById(R.id.btn_chart);

        btn1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent frm_insert = new Intent(MainActivity.this,InsertData.class);
                startActivity(frm_insert);
            }
        });
        btn_view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent frm_view = new Intent(MainActivity.this,ViewActivity.class);
                startActivity(frm_view);
            }
        });
        btn_chart.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent frm_chart = new Intent(MainActivity.this,Graph1Activity.class);
                startActivity(frm_chart);
            }
        });

    }


}
