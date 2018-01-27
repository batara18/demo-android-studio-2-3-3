package com.example8.mbatara.demo_1;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.R.attr.button;

public class LoginActivity extends Activity {

    EditText txt_user,txt_password,txt_host;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.form_login);

        txt_user = (EditText) findViewById(R.id.txt_user);
        txt_password = (EditText) findViewById(R.id.txt_password);
        txt_host = (EditText) findViewById(R.id.txt_host);
        btn_login = (Button) findViewById(R.id.btn_login);

        btn_login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // call method mainList()
                login();
            }
        });

        // alert 1
        /*
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Sample Alert");
        AlertDialog alert = builder.create();
        alert.show();
        */
        // alert 2
        /*
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Doctor");
        alert.setMessage("message");
        alert.setPositiveButton("OK",null);
        alert.show();
        */
        // alert 3
        showAlert();
    }

    private void showAlert(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure, You wanted to make decision");
                alertDialogBuilder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                // if button yes clicked
                                Toast.makeText(LoginActivity.this,"You clicked yes button",Toast.LENGTH_LONG).show();
                            }
                        });

        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // if button no clicked
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void login()
    {
        //final EditText txt_host = (EditText) findViewById(R.id.txt_host);
        String url = "https://"+txt_host.getText().toString()+"/demo-login";

        final StringRequest stringRequest = new StringRequest(+
                Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Log.d("RETURN LOGIN: ",response);
                        try {
                            JSONObject json = new JSONObject(response);
                            // JSONArray arr = json.getJSONArray("stat");
                            // JSONObject obj = json.getJSONObject("stat");
                            String stat = json.getString("stat");

                            if (stat=="1") {
                                //final EditText txt_user = (EditText) findViewById(R.id.txt_user);

                                SharedPreferences sp = getSharedPreferences("my_pref", Activity.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putString("pref_user", txt_user.getText().toString());
                                editor.putString("pref_password", txt_password.getText().toString());

                                editor.putString("pref_host", txt_host.getText().toString());

                                editor.commit();
                                Toast.makeText(LoginActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                Intent main_activity = new Intent(LoginActivity.this,MainActivity.class);
                                startActivity(main_activity);
                            } else {
                                Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        } catch (Exception e) {
                            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
                params.put("txt_user",txt_user.getText().toString());
                params.put("txt_password",txt_password.getText().toString());
                return params;
            }
        };
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }
}
