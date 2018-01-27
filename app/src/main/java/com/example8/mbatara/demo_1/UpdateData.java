package com.example8.mbatara.demo_1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateData extends AppCompatActivity {

    EditText txt_1,txt_2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_update);

        Intent frm_update = getIntent();
        Bundle bd = frm_update.getExtras();
        if(bd != null)
        {
            txt_1 = (EditText)findViewById(R.id.txt_col_1);
            txt_2 = (EditText)findViewById(R.id.txt_col_2);
            String col_1 = (String) bd.get("col_1");
            String col_2 = (String) bd.get("col_2");
            txt_1.setText(col_1);
            txt_2.setText(col_2);
//            Toast.makeText(this, "DATA STRING"+col_1, Toast.LENGTH_SHORT).show();
        }

    }
}
