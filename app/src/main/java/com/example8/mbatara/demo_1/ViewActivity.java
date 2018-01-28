package com.example8.mbatara.demo_1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ViewActivity extends AppCompatActivity {

    ListView list1;
    ArrayList<SetterGeter> list_array = new ArrayList<SetterGeter>();
    AdapterList adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        list1 = (ListView)findViewById(R.id.list1);

        // set listener event onClick at list1.list_array
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String col_1 = list_array.get(position).getColumn_1().toString();
                String col_2 = list_array.get(position).getColumn_2().toString();
                Toast.makeText(getBaseContext(),col_1,Toast.LENGTH_SHORT).show();

                Intent frm_update = new Intent(ViewActivity.this,UpdateData.class);
                frm_update.putExtra("col_1",col_1);
                frm_update.putExtra("col_2",col_2);
                startActivity(frm_update);
            }
        });

        view1();
    }

    private void view1()
    {
        // define end-point url rest
        SharedPreferences sp = getSharedPreferences("my_pref", Activity.MODE_PRIVATE);
        String  pref_host = sp.getString("pref_host", "host null");

        String url = "https://"+pref_host+"/demo-main";
        final StringRequest stringRequest = new StringRequest(+
                Request.Method.GET, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Log.d("log_response",response);
                        try {
                            JSONObject json = new JSONObject(response);
                            JSONArray arr = json.getJSONArray("datas");

                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject obj = arr.getJSONObject((i));
                                String col_1 = obj.getString("col_1");
                                String col_2 = obj.getString("col_2");
                                String col_3 = obj.getString("col_3");
                                list_array.add(new SetterGeter(col_1,col_2,col_3));
                            }

                            // for show data
                            adapter = new AdapterList(getApplicationContext(), list_array);
                            list1.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },

                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Log.d("log_response", String.valueOf(error));
                    }
                }
        )
        {
            //
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                SharedPreferences sp = getSharedPreferences("my_pref", Activity.MODE_PRIVATE);
                String  pref_user = sp.getString("pref_user", "user null");
                String  pref_password = sp.getString("pref_password", "password null");

                Map<String, String> headers = new HashMap<>();
                String credentials = pref_user+":"+pref_password;
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
