package com.example.litho.picasso;

import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class Utilities {

    public static String GetAssetJsonData(Context context, String itemType) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(itemType);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }

        Log.e("data", json);
        return json;
    }
}
