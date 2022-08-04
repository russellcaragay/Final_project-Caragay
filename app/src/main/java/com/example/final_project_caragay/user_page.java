package com.example.final_project_caragay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class user_page extends AppCompatActivity {

    Button signout;
    TextView username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        signout = (Button) findViewById(R.id.signout);
        username = (TextView) findViewById(R.id.user_txt);

        username.setText(userinfo.user_name);

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(user_page.this, MainActivity.class);
                toast_message.message(getApplicationContext(),"Successfully Signed Out");
                startActivity(in);
                finish();
            }
        });

    }
}