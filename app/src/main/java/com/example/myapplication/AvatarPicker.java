package com.example.myapplication;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.GridView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AvatarPicker extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avatar_picker);

        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new ImageAdapter(this));

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            // Pass the selected image resource ID back to the calling activity
            Intent resultIntent = new Intent();
            resultIntent.putExtra("selectedImageId", ImageAdapter.mThumbIds[position]);
            setResult(RESULT_OK, resultIntent);
            finish();
        });
    }
}
