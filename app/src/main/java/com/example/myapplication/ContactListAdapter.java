package com.example.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ContactListAdapter extends CursorAdapter {

    public ContactListAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        return inflater.inflate(R.layout.contact_list_item, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView textViewName = view.findViewById(R.id.textViewContactName);
        TextView textViewEmail = view.findViewById(R.id.textViewContactEmail);
        ImageView imageViewPhoto = view.findViewById(R.id.imageViewContactPhoto);

        String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
        String email = cursor.getString(cursor.getColumnIndexOrThrow("email"));
        byte[] photoData = cursor.getBlob(cursor.getColumnIndexOrThrow("photo"));

        textViewName.setText(name);
        textViewEmail.setText(email);

        // Set contact photo if available
        if (photoData != null && photoData.length > 0) {
            Bitmap photoBitmap = BitmapFactory.decodeByteArray(photoData, 0, photoData.length);
            imageViewPhoto.setImageBitmap(photoBitmap);
        } else {
            // Set a default photo if no photo is available
            imageViewPhoto.setImageResource(R.drawable.baseline_account_circle_24);
        }
    }
}
