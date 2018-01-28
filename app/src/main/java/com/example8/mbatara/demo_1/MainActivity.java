package com.example8.mbatara.demo_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    Button btn1,btn_view;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        btn1 = (Button)findViewById(R.id.btn_add);
        btn_view = (Button)findViewById(R.id.btn_view);

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

    }


}
