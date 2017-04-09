package com.example.nightmare.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Logout extends AppCompatActivity implements View.OnClickListener {
    EditText name;
    //TextView welcome;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        name = (EditText) findViewById(R.id.t1);
        //welcome = (TextView) findViewById(R.id.fin);
        logout = (Button) findViewById(R.id.b3);

        Intent intent = getIntent();
        String usrname = intent.getStringExtra("username");

        //String mess = "Welcome to your user area";
        //welcome.setText(mess);
        name.setText(usrname);

        logout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.b3:
                startActivity(new Intent(this, MainActivity.class));
                break;

        }
    }
}
