package com.example.victorhinaux.timetofit;

/**
 * Created by mathp on 27/12/2018.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Database extends SQLiteOpenHelper {

    private static final String TAG = "Database";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "time2fitDBv2.db";


    private static final String COLUMN_ID = "id";

    //Contacts table
    private static final String COLUMN_FIRSTNAME = "firstname";
    private static final String COLUMN_LASTNAME = "lastname";
    private static final String COLUMN_EMAILC= "emailC";
    private static final String COLUMN_PASS = "pass";

    private static final String TABLE_CONTACTS="create table contacts (id integer primary key not null, firstname text not null, lastname text not null, emailC text not null, pass text not null);";

    SQLiteDatabase db;

    //Trainers table
    private static final String COLUMN_NAMET = "nameT";
    private static final String COLUMN_EMAILT = "emailT";
    private static final String COLUMN_NUMERO = "numero";
    private static final String COLUMN_TRAININGSNAMES = "trainingsnames";

    private Trainer Jimmy = new Trainer("Jimmy", "jimmy@gmail.com", "0618291054");
    private Trainer Marina = new Trainer("Marina", "marina@gmail.com", "0793183475");

    private static final String TABLE_TRAINERS="create table trainers (id integer not null, nameT text not null, emailT text not null, numero text not null, trainingsNames text not null);";

    public Database(Context context)
    {
        super(context, DATABASE_NAME,    null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CONTACTS);
        db.execSQL(TABLE_TRAINERS);
        this.db = db;
    }

    public void insertContact(Contact c)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //create a string query
        String query = "select  * from contacts ";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_FIRSTNAME, c.getFirstName());
        values.put(COLUMN_LASTNAME, c.getLastName());
        values.put(COLUMN_EMAILC, c.getEmail());
        values.put(COLUMN_PASS, c.getPass());

        //this will insert the content in the database

        db.insert("contacts", null, values);
    }

    public void insertTrainer(Trainer t)
    {
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        //create a string query
        String query = "select  * from trainers ";
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();

        values.put(COLUMN_ID, count);
        values.put(COLUMN_NAMET, t.getName());
        values.put(COLUMN_EMAILT, t.getEmail());
        values.put(COLUMN_NUMERO, t.getNumero());
        values.put(COLUMN_TRAININGSNAMES, "");

        //this will insert the content in the database by ignoring if the set of values already exist
        //String insertQuery = "INSERT IGNORE INTO trainers SET id = "+/*String.valueOf(count)+*/"1"+",name = '"+t.getName()+"', emailT = '"+t.getEmail()+"', numero = '"+t.getNumero()+"', trainingsNames = '"+/*t.trainingsNames()*/""+"';";
        db.insert("trainers", null, values);
    }

    public String searchPass(String name){

        db = this.getReadableDatabase();
        String query = "select emailC, pass from contacts";
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b="not found";
        if(cursor.moveToFirst()){
            do{
                a=cursor.getString(0);

                if(a.equals(name)){
                    b=cursor.getString(1);
                    break;
                }
            }
            while (cursor.moveToNext());
        }
        return b;
    }

    public void doAQuery(String query)
    {
        db.execSQL(query);
    }

    public String trainerEmail(String name){
        db = getWritableDatabase();
        insertTrainer(Jimmy);
        insertTrainer(Marina);
        String result = "";

        String query = "SELECT emailT FROM trainers WHERE nameT = '"+name+"';";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            result = cursor.getString(cursor.getColumnIndex("emailT"));
        }
        cursor.close();
        return result;
    }

    public String trainerNumero(String name){
        db = getWritableDatabase();
        String result = "";

        String query = "SELECT numero FROM trainers WHERE nameT = '"+name+"';";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            result = cursor.getString(cursor.getColumnIndex("numero"));
        }
        cursor.close();
        return result;
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS "+TABLE_CONTACTS+ " ,"+TABLE_TRAINERS;
        db.execSQL(query);
        this.onCreate(db);
    }
}
