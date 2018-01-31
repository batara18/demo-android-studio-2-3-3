package com.example8.mbatara.demo_1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Graph1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph1);

//        BarChart barChart = (BarChart) findViewById(R.id.chart);
//
//        ArrayList<String> labels = new ArrayList<String>();
//        labels.add("January");
//        labels.add("February");
//        labels.add("March");
//        labels.add("April");
//        labels.add("May");
//        labels.add("June");

        // for create Grouped Bar chart
//        ArrayList<BarEntry> group1 = new ArrayList<>();
//        group1.add(new BarEntry(23f, 0));
//        group1.add(new BarEntry(8f, 1));
//        group1.add(new BarEntry(6f, 2));
//        group1.add(new BarEntry(12f, 3));
//        group1.add(new BarEntry(18f, 4));
//        group1.add(new BarEntry(9f, 5));

//        BarDataSet barDataSet1 = new BarDataSet(group1, "Group 1");
//        barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);
//
//        ArrayList<BarDataSet> datasets = new ArrayList<>();
//        datasets.add(barDataSet1);
//
//        BarData data = new BarData(labels, datasets);
//        barChart.setData(data);
//        barChart.animateY(5000);
        viewGraph();
    }

    private void viewGraph()
    {
        SharedPreferences sp = getSharedPreferences("my_pref", Activity.MODE_PRIVATE);
        String  pref_host = sp.getString("pref_host", "host null");

        String url = "https://"+pref_host+"/demo-graph-1";
        final StringRequest stringRequest = new StringRequest(+
                Request.Method.POST, url,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Log.d("log_response",response);
                        try {
                            JSONObject json = new JSONObject(response);
                            JSONArray arr = json.getJSONArray("datas");

                            BarChart barChart = (BarChart) findViewById(R.id.chart);

                            ArrayList<String> labels = new ArrayList<String>();
                            labels.add("January");
                            labels.add("February");
                            labels.add("March");
                            labels.add("April");
                            labels.add("May");
                            labels.add("June");

                            ArrayList<BarEntry> group1 = new ArrayList<>();

                            for (int i = 0; i < arr.length(); i++) {
                                JSONObject obj = arr.getJSONObject((i));
                                Integer col_1 = obj.getInt("col_1");
                                group1.add(new BarEntry(col_1, i));
                            }

                            BarDataSet barDataSet1 = new BarDataSet(group1, "Group 1");
                            barDataSet1.setColors(ColorTemplate.COLORFUL_COLORS);

                            ArrayList<BarDataSet> datasets = new ArrayList<>();
                            datasets.add(barDataSet1);

                            BarData data = new BarData(labels, datasets);
                            barChart.setData(data);
                            barChart.animateY(5000);

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
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                Map <String,String> params = new HashMap<>();
                params.put("params_col_1","1");
                params.put("params_col_2","2");
                return params;
            }
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
