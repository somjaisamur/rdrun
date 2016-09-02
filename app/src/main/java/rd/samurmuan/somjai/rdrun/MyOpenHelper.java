package rd.samurmuan.somjai.rdrun;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 9/2/2016.
 */
public class MyOpenHelper  extends SQLiteOpenHelper{
    //Explicit
    //create database must have name and version
    public static final String database_name = "rdRun.db";
    private static final int database_version = 1;
    //create table structure
    private static final String create_user_table = "create table userTABLE (" +
            "_id integer primary key," +
            "User text," +
            "Password text," +
            "Name text," +
            "Surname text," +
            "Avata text," +
            "idUser text);";






    public MyOpenHelper(Context context) {
        //การทำงาน 1 constructor 2 find databasename 3 have userpassword? 4 version ? ื
        super(context,database_name,null,database_version);


    }// Constructor

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_user_table);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }
}//main class

