package com.example.litho.picasso;

import android.content.Context;

import com.squareup.picasso.Picasso;

public final class PicassoProvider {

    private static Picasso instance;

    public static Picasso get() {
        if (instance == null) {
            synchronized (PicassoProvider.class) {
                if (instance == null) {
                    Context autoContext = Application.getContext();
                    if (autoContext == null) {
                        throw new NullPointerException("context == null");
                    }
                    instance = new Picasso.Builder(autoContext).build();
                }
            }
        }
        return instance;
    }

    private PicassoProvider() {
        throw new AssertionError("No instances.");
    }
}
