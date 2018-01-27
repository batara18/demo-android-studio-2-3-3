package com.example8.mbatara.demo_1;

//import android.content.Intent;
import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

//import org.json.JSONArray;
//import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class InsertData extends AppCompatActivity
{
    // define variable on public scope
    EditText txt_column_1,txt_column_2;
    Button btn_submit,btn_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_add);

        SharedPreferences sp = getSharedPreferences("my_pref", Activity.MODE_PRIVATE);
        String  pref_user = sp.getString("pref_user", "user null");
        String  pref_password = sp.getString("pref_password", "password null");

        Toast.makeText(InsertData.this, "User : "+pref_user, Toast.LENGTH_SHORT).show();
        Toast.makeText(InsertData.this, "Password : "+pref_password, Toast.LENGTH_SHORT).show();

        txt_column_1 = (EditText) findViewById(R.id.txt_col_1);
        txt_column_2 = (EditText) findViewById(R.id.txt_col_2);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);

        // define event listener for button submit
        btn_submit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // call method mainList()
                mainPage();
            }
        });
        // define event listener for button cancel
        btn_cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

            }
        });
    }

    private void mainPage()
    {
        SharedPreferences sp = getSharedPreferences("my_pref", Activity.MODE_PRIVATE);
        String  pref_host = sp.getString("pref_host", "host null");
        String url = "https://"+pref_host+"/demo-post-1";

        final StringRequest stringRequest = new StringRequest(+
                Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Log.d("RETURN : ",response);
                        try {
                            JSONObject json = new JSONObject(response);
                            // JSONArray arr = json.getJSONArray("stat");
                            // JSONObject obj = json.getJSONObject("stat");
                            String stat = json.getString("stat");

                            if (stat=="1") {
                                Toast.makeText(InsertData.this, "Success", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(InsertData.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(InsertData.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }
                },

                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.d("log_response : ", String.valueOf(error));
                    }
                }
        )
        // set parameter for method POST
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map <String,String> params = new HashMap<>();
                params.put("params_col_1",txt_column_1.getText().toString());
                params.put("params_col_2",txt_column_2.getText().toString());
                return params;
            }
            //
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                String credentials = "user_middle:Pun1n4R18#";
                String auth = "Basic "
                        + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", auth);
                return headers;
            }
            //
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }

}
