package com.example.final_project_caragay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


public class admin_page extends AppCompatActivity {

    EditText Name,Pass,delete,updateold,updatenew,updatepass;
    RadioButton radio1,radio2;
    caragay_database helper;
    Button delbtn, signout;

    caragay_database DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        Name = (EditText) findViewById(R.id.editName);
        Pass = (EditText) findViewById(R.id.editPass);
        delete = (EditText) findViewById(R.id.delete);

        updateold = (EditText) findViewById(R.id.currentname);
        updatenew = (EditText) findViewById(R.id.newname);
        updatepass = (EditText) findViewById(R.id.newpass);

        radio1 = (RadioButton) findViewById(R.id.admin);
        radio2 = (RadioButton) findViewById(R.id.user);

        delbtn = (Button) findViewById(R.id.deletebtn);
        signout = (Button) findViewById(R.id.outbtn);

        DB = new caragay_database(this);

        helper = new caragay_database(this);

        String usertype = usertypeinfo.user_type;

        Boolean privilege = true;

        if (usertype.equals("admin")){
            radio1.setEnabled(false);
            privilege = false;
            usertypeinfo.user_type = "";
        }

        Boolean finalPrivilege = privilege;
        delbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uname = delete.getText().toString();
                Boolean admininputchecker = DB.special_rolechecker(uname);

                if(uname.isEmpty())
                {
                    toast_message.message(getApplicationContext(),"Enter Data");
                }

                else if (finalPrivilege == false){
                    if (admininputchecker == true){
                        toast_message.message(getApplicationContext(),"No Permission to Delete Admin Data!");
                        delete.setText("");
                    }
                    else{
                        int a= helper.delete(uname);
                        if(a<=0)
                        {
                            toast_message.message(getApplicationContext(),"Unsuccessful");
                            delete.setText("");
                        }
                        else
                        {
                            toast_message.message(getApplicationContext(), "Delete Request Successful");
                            delete.setText("");
                        }
                    }
                }
                else{
                    int a= helper.delete(uname);
                    if(a<=0)
                    {
                        toast_message.message(getApplicationContext(),"Unsuccessful");
                        delete.setText("");
                    }
                    else
                    {
                        toast_message.message(getApplicationContext(), "Delete Request Successful");
                        delete.setText("");
                    }
                }

            }
        });

        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(admin_page.this, MainActivity.class);
                toast_message.message(getApplicationContext(),"Successfully Signed Out");
                startActivity(in);
                finish();
            }
        });

    }

    public void addUser(View view)
    {
        String name = Name.getText().toString();
        String pass = Pass.getText().toString();
        String role = " ";

        if (radio1.isChecked()){
            role = "admin";
        }

        else if (radio2.isChecked()){
            role = "user";
        }

        Boolean addchecker = DB.user_rolechecker(name,role);

        if(name.isEmpty() || pass.isEmpty() || (!radio1.isChecked() && !radio2.isChecked()))
        {
            toast_message.message(getApplicationContext(),"Check your inputs: (Name, Message, and Role)");
        }
        else
        {
            if (addchecker == true){
                toast_message.message(getApplicationContext(),"Already Exists");
            }
            else{
                long id = helper.insertData(name,pass,role);
                if(id<=0)
                {
                    toast_message.message(getApplicationContext(),"Insertion Unsuccessful");
                } else
                {
                    toast_message.message(getApplicationContext(),"Insertion Successful");
                    Name.setText("");
                    Pass.setText("");

                }
            }

        }
    }

    public void viewdata(View view)
    {
        String data = helper.getData();
        dialog_message.dialog(this,data);
    }

    public void update( View view)
    {
        String current = updateold.getText().toString();
        String newname = updatenew.getText().toString();
        String newpass = updatepass.getText().toString();

        if(current.isEmpty() || newname.isEmpty() || newpass.isEmpty())
        {
            toast_message.message(getApplicationContext(),"Enter Data");
        }
        else
        {
            int a= helper.updateName( current, newname, newpass);
            if(a<=0)
            {
                toast_message.message(getApplicationContext(),"Unsuccessful");
                updateold.setText("");
                updatenew.setText("");
                updatepass.setText("");
            } else {
                toast_message.message(getApplicationContext(),"Updated");
                updateold.setText("");
                updatenew.setText("");
                updatepass.setText("");
            }
        }

    }


}