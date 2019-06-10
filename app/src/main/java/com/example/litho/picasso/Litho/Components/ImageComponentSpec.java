package com.example.litho.picasso.Litho.Components;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import androidx.swiperefreshlayout.widget.CircularProgressDrawable;

import com.example.litho.picasso.GlideProvider;
import com.example.litho.picasso.PicassoProvider;
import com.example.litho.picasso.R;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.squareup.picasso.NetworkPolicy;

@LayoutSpec
public class ImageComponentSpec {

    @OnCreateLayout
    static Component OnCreateLayout(ComponentContext c, @Prop String imageUrl) {

        return GlideImage.create(c)
                .imageUrl(imageUrl)
                .fitCenter(false)
                .asBitmap(false)
                .skipMemoryCache(false)
                .failureImage(c.getAndroidContext().getDrawable(R.drawable.outline_broken_image_black_36))
                .build();
    }
}
