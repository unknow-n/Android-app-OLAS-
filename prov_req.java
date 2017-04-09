package com.example.nightmare.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class prov_req extends AppCompatActivity {
    Button submit;
    EditText com_name,owner_name,mob_no;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_prov_req);

        com_name = (EditText) findViewById(R.id.comptext);
        owner_name = (EditText) findViewById(R.id.owntext);
        mob_no = (EditText) findViewById(R.id.mobtext);
        submit = (Button) findViewById(R.id.breq1);



            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(com_name.length() == 0 || owner_name.length() == 0 || mob_no.length() == 0 ){
                        AlertDialog.Builder builder = new AlertDialog.Builder(prov_req.this);
                        builder.setMessage("Request Failed\n\nall the above mentioned field are necessary to register")
                                .setNegativeButton("Retry", null)
                                .create()
                                .show();
                    }
                    else {
                        if (mob_no.length() == 10) {

                            final String com_nam = com_name.getText().toString();
                            final String owner_nam = owner_name.getText().toString();
                            final String mob_n = mob_no.getText().toString();

                            Response.Listener<String> responseListener = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject JsonResponse = new JSONObject(response);
                                        boolean success = JsonResponse.getBoolean("success");

                                        if (success) {
                                            Intent subintent = new Intent(prov_req.this, MainActivity.class);
                                            prov_req.this.startActivity(subintent);
                                        } else {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(prov_req.this);
                                            builder.setMessage("Request Failed")
                                                    .setNegativeButton("Retry", null)
                                                    .create()
                                                    .show();
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };

                            ProviderRequest providerreq = new ProviderRequest(com_nam, owner_nam, mob_n, responseListener);
                            RequestQueue queue = Volley.newRequestQueue(prov_req.this);
                            queue.add(providerreq);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(prov_req.this);
                            builder.setMessage("Request Failed\n\nplease enter a 10-digit mobile number")
                                    .setNegativeButton("Retry", null)
                                    .create()
                                    .show();
                        }
                    }

                }
            });
    }
}
