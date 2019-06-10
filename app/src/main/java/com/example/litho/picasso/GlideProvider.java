package com.example.litho.picasso;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

public class GlideProvider {

    public static RequestManager get() {
        if (instance == null) {
            synchronized (PicassoProvider.class) {
                if (instance == null) {
                    Context autoContext = Application.getContext();
                    if (autoContext == null) {
                        throw new NullPointerException("context == null");
                    }
                    instance = Glide.with(autoContext);
                }
            }
        }
        return instance;
    }

    private static RequestManager instance;

    private GlideProvider() {
        throw new AssertionError("No instances.");
    }
}
