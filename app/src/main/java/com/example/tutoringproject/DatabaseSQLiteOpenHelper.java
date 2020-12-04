package com.example.tutoringproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseSQLiteOpenHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "STUDENT DATA";
    private static final int DB_VERSION = 1;

    public DatabaseSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String STUDENTS = "CREATE TABLE STUDENTS (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "USERNAME TEXT," +
                "PASSWORD TEXT," +
                "LEVEL INTEGER," +
                "FIRSTNAME TEXT ," +
                "LASTNAME TEXT," +
                "PHONENUMBER INTEGER," +
                "ADDRESS TEXT);";

        String TUTORS = "CREATE TABLE TUTORS (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "USERNAME TEXT," +
                "PASSWORD TEXT," +
                "COURSES TEXT," +
                "FIRSTNAME TEXT ," +
                "LASTNAME TEXT," +
                "PHONENUMBER INTEGER," +
                "ADDRESS TEXT);";

        String Schedules = "CREATE TABLE SCHEDULES (" +
                "DATE TEXT," +
                "TIME TEXT," +
                "SUBJECT TEXT," +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "STUDENT_ID INTEGER," +
                "TUTOR_ID INTEGER," +
                "FOREIGN_KEY TUTOR_ID REFERENCES TUTORS(_id)," +
                "FOREIGN KEY (Student_id) REFERENCES STUDENTS(_id));";

        db.execSQL(STUDENTS);
        db.execSQL(TUTORS);
        db.execSQL(Schedules);
    }
    private void insertStudent(SQLiteDatabase db,
                               String answer,
                               int imageID) {
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
