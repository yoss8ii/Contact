package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
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
