package com.example.final_project_caragay;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class caragay_database {
    myDbHelper myhelper;
    public caragay_database(Context context)
    {
        myhelper = new myDbHelper(context);
    }

    static class myDbHelper extends SQLiteOpenHelper
    {
        private static final String DATABASE_NAME = "myDatabase";    // Database Name
        private static final String TABLE_NAME = "myTable";   // Table Name
        private static final int DATABASE_Version = 1;   // Database Version
        private static final String UID="_id";     // Column I (Primary Key)
        private static final String NAME = "Name";    //Column II
        private static final String MyPASSWORD= "Password";    // Column III
        private static final String ROLE= "role";    // Column IV
        private static final String CREATE_TABLE = "CREATE TABLE "+TABLE_NAME+
                " ("+UID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+NAME+" VARCHAR(255) ,"+ MyPASSWORD+" VARCHAR(225), "+ ROLE+" VARCHAR(225));";
        private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
        private Context context;

        public myDbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_Version);
            this.context=context;
        }

        public void onCreate(SQLiteDatabase db) {
            try {
                db.execSQL(CREATE_TABLE);
                this.insertDefault(db, "admin1", "admin1","admin");
                this.insertDefault(db, "user1", "user1","user");

            } catch (Exception e) {
                toast_message.message(context,""+e);
            }
        }

        public void insertDefault(SQLiteDatabase db, String name, String pass, String role)
        {
            ContentValues contentValues = new ContentValues();
            contentValues.put(myDbHelper.NAME, name);
            contentValues.put(myDbHelper.MyPASSWORD, pass);
            contentValues.put(myDbHelper.ROLE, role);
            db.insert(myDbHelper.TABLE_NAME, null , contentValues);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try {
                toast_message.message(context,"OnUpgrade");
                db.execSQL(DROP_TABLE);
                onCreate(db);
            }catch (Exception e) {
                toast_message.message(context,""+e);
            }
        }
    }


    public long insertData(String name, String pass, String role)
    {
        SQLiteDatabase dbb = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME, name);
        contentValues.put(myDbHelper.MyPASSWORD, pass);
        contentValues.put(myDbHelper.ROLE, role);
        long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
        return id;
    }

    public String getData()  {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] columns = {myDbHelper.UID,myDbHelper.NAME,myDbHelper.MyPASSWORD,myDbHelper.ROLE};
        Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);
        StringBuffer buffer= new StringBuffer();
        while (cursor.moveToNext())   {
            @SuppressLint("Range") int cid =cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
            @SuppressLint("Range") String name =cursor.getString(cursor.getColumnIndex(myDbHelper.NAME));
            @SuppressLint("Range") String password =cursor.getString(cursor.getColumnIndex(myDbHelper.MyPASSWORD));
            @SuppressLint("Range") String role =cursor.getString(cursor.getColumnIndex(myDbHelper.ROLE));
            buffer.append(cid + "\nusername: " + name + "\npassword: " + password + "\nrole: "+ role +" \n\n");
        }
        return buffer.toString();
    }

    public int updateName(String oldName , String newName , String newPass)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(myDbHelper.NAME,newName);
        contentValues.put(myDbHelper.MyPASSWORD,newPass);
        String[] whereArgs= {oldName};
        int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.NAME+" = ?",whereArgs );
        return count;
    }

    public int delete(String uname)
    {
        SQLiteDatabase db = myhelper.getWritableDatabase();
        String[] whereArgs ={uname};

        int count =db.delete(myDbHelper.TABLE_NAME ,myDbHelper.NAME+" = ?",whereArgs);
        return  count;
    }

    public Boolean user_rolechecker (String username, String role){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from myTable where Name = ? and Role = ?", new String[] {username,role});
        if (cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean checker (String username, String password){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from myTable where Name = ? and Password = ?", new String[] {username,password});
        if (cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean rolechecker (String username, String password){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from myTable where Name = ? and Password = ? and Role = ?", new String[] {username,password,"admin"});
        if (cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean special_rolechecker (String username){
        SQLiteDatabase db = myhelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from myTable where Name = ? and Role = ?", new String[] {username,"admin"});
        if (cursor.getCount()>0){
            return true;
        }
        else {
            return false;
        }
    }




}
