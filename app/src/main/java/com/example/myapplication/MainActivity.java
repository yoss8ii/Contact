package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private ContactListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);
        displayContacts();

        ListView listView = findViewById(R.id.contactListView);
        FloatingActionButton fabAddContact = findViewById(R.id.fabAddContact);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            // Handle item click, e.g., open contact details activity
        });
        listView.setOnItemLongClickListener((parent, view, position, id) -> {
            // Retrieve the contact ID from the cursor
            Cursor cursor = (Cursor) parent.getItemAtPosition(position);
            @SuppressLint("Range") long contactIdToDelete = cursor.getLong(cursor.getColumnIndex("_id"));

            // Delete the contact
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("Confirmation")
                    .setMessage("Vous étes sûre de supprimer ce contact ?")
                    .setPositiveButton("Oui", (dialog, which) -> {
                        // Delete the contact
                        dbHelper.deleteContact(contactIdToDelete);
                        // Update the cursor and refresh the ListView
                        adapter.swapCursor(dbHelper.getAllContacts());
                    })
                    .setNegativeButton("Non", null)
                    .show();
            return true;
        });

        fabAddContact.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AjouterContact.class));
        });
    }

    private void displayContacts() {
        Cursor cursor = dbHelper.getAllContacts();

        // Assuming you have a ContactListAdapter, modify this line accordingly
        adapter = new ContactListAdapter(this, cursor, 0);

        ListView listView = findViewById(R.id.contactListView);
        listView.setAdapter(adapter);
    }

}
