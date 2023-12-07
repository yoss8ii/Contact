package com.example.myapplication;

import android.graphics.Bitmap;
import java.io.ByteArrayOutputStream;

public class ImageUtils {

    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }
}
