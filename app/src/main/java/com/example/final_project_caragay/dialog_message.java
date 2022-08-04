package com.example.final_project_caragay;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

public class dialog_message {
    public static void dialog (Context context, String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setMessage(msg);
        builder.setTitle("User and Admin Data:");
        builder.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }
}
