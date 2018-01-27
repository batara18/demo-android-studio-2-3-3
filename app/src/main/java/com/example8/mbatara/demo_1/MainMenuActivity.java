package com.example8.mbatara.demo_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        ImageView img_1 = (ImageView) findViewById(R.id.menuicon1);
        img_1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainMenuActivity.this, "MENU 1", Toast.LENGTH_SHORT).show();
            }
        });

        ImageView img_2 = (ImageView) findViewById(R.id.menuicon2);
        img_2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainMenuActivity.this, "MENU 2", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
