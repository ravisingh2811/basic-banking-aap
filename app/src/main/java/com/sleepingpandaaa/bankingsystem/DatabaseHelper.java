package com.sleepingpandaaa.bankingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "user_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into user_table values(9000238438,'Ravi singh',94729409.00,'ravi@gmail.com','XXXXXXXXXXXX1244','ABC09876543')");
        db.execSQL("insert into user_table values(9111284821,'Dimpal',582983.67,'dimpal@gmail.com','XXXXXXXXXXXX2541','BCA98765432')");
        db.execSQL("insert into user_table values(9222222229,'Vimlesh',1359349.56,'vimlesh@gmail.com','XXXXXXXXXXXX3252','CAB87654321')");
        db.execSQL("insert into user_table values(9333332343,'Manoj',150093893.01,'manoj@gmail.com','XXXXXXXXXXXX5323','ABC76543210')");
        db.execSQL("insert into user_table values(9444444444,'Renu',2603334.48,'renu@gmail.com','XXXXXXXXXXXX2352','BCA65432109')");
        db.execSQL("insert into user_table values(9553238492,'Jaishree',945653.16,'jaishree@gmail.com','XXXXXXXXXXXX3765','CAB54321098')");
        db.execSQL("insert into user_table values(9649289266,'Chotak',593666.00,'chotak@gmail.com','XXXXXXXXXXXX6744','ABC43210987')");
        db.execSQL("insert into user_table values(9999999999,'Saloni',27386.90,'saloni@gmail.com','XXXXXXXXXXXX5636','ABC10987654')");
        db.execSQL("insert into user_table values(9999398938,'Saloni',27386.90,'saloni@gmail.com','XXXXXXXXXXXX5636','ABC10987654')");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from user_table except select * from user_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update user_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
