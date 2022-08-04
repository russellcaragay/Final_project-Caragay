package com.example.final_project_caragay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button loginbtn;
    EditText user,pass;
    caragay_database DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        loginbtn = (Button) findViewById(R.id.loginbtn);

        user = (EditText) findViewById(R.id.username);
        pass = (EditText) findViewById(R.id.password);

        DB = new caragay_database(this);

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userinput = user.getText().toString();
                String passwordinput = pass.getText().toString();

                Boolean inputchecker = DB.checker(userinput,passwordinput);
                Boolean adminchecker = DB.rolechecker(userinput,passwordinput);

                if (userinput.equals("admin")  && passwordinput.equals("admin")){
                    usertypeinfo.user_type = "superadmin";
                    toast_message.message(getApplicationContext(),"Logged in as SuperAdmin");
                    Intent in = new Intent(MainActivity.this, admin_page.class);
                    startActivity(in);
                    finish();
                }

                if (inputchecker==true){
                    if(adminchecker==true){
                        usertypeinfo.user_type = "admin";
                        toast_message.message(getApplicationContext(),"Logged in as Admin");
                        Intent in = new Intent(MainActivity.this, admin_page.class);
                        startActivity(in);
                        finish();
                    }
                    else{
                        userinfo.user_name = userinput;
                        toast_message.message(getApplicationContext(),"Logged in as User");
                        Intent in = new Intent(MainActivity.this, user_page.class);
                        startActivity(in);
                        finish();
                    }
                }
                else{
                    toast_message.message(getApplicationContext(),"Check your inputs!");
                }


            }
        });
    }
}