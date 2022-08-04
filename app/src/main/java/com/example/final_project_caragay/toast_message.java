package com.example.final_project_caragay;

import android.content.Context;
import android.widget.Toast;

public class toast_message {
    public static void message(Context context, String message){
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();

    }


}
