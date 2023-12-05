package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class AjouterContact  extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    private static final int PICK_PHOTO_REQUEST_CODE = 1;

    private EditText editTextName;
    private EditText editTextEmail;
    private ImageView imageViewContactPhoto;
    private byte[] selectedPhoto; // byte array to store the selected photo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_contact);

        dbHelper = new DatabaseHelper(this);


        editTextName = findViewById(R.id.editTextContactName);
        editTextEmail = findViewById(R.id.editTextContactEmail);
        imageViewContactPhoto = findViewById(R.id.imageViewContactPhoto);

        Button buttonAddContact = findViewById(R.id.buttonAddContact);
        buttonAddContact.setOnClickListener(v -> addNewContact());

        imageViewContactPhoto.setOnClickListener(v -> pickPhoto());
    }

    private void addNewContact() {
        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();

        if (dbHelper != null) {

            if (!name.isEmpty() && !email.isEmpty()) {
                // Create a new Contact object
                Contact newContact = new Contact();
                newContact.setName(name);
                newContact.setEmail(email);
                newContact.setPhoto(selectedPhoto); // Set the selected photo

                // Add the new contact to the database
                long contactId = dbHelper.addContact(newContact);

                if (contactId != -1) {
                    Toast.makeText(this, "Contact ajouté avec succée ", Toast.LENGTH_SHORT).show();
                    // Contact added successfully
                    Intent intent = new Intent(AjouterContact.this, MainActivity.class);
                    startActivity(intent);
                    finish();  // Close the AddContactActivity
                } else {
                    Toast.makeText(this, "Echec d'ajout du contact !!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Un ou plusieurs champs sont vides !!", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, "Erreur !!", Toast.LENGTH_SHORT).show();
        }
    }

    private void pickPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_PHOTO_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_PHOTO_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                imageViewContactPhoto.setImageBitmap(bitmap);

                // Convert the image to a byte array and store it
                selectedPhoto = ImageUtils.getBytes(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
