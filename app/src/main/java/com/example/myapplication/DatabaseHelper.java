package com.example.myapplication;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;

    // Contacts table creation query
    private static final String CREATE_TABLE_CONTACTS = "CREATE TABLE contacts " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, email TEXT, photo BLOB)";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public long addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", contact.getName());
        values.put("email", contact.getEmail());
        values.put("photo", contact.getPhoto()); // Assuming contact.getPhoto() returns a byte array

        return db.insert("contacts", null, values);
    }

    public Cursor getAllContacts() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = {"id AS _id", "name", "email", "photo"};
        return db.query("contacts", columns, null, null, null, null, null);
    }

}


