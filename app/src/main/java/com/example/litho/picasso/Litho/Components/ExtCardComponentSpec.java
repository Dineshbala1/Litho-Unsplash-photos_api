package com.example.litho.picasso.Litho.Components;

import android.graphics.Color;

import com.example.litho.picasso.Model.UnSplash.Collection;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.widget.ListRecyclerConfiguration;
import com.facebook.litho.sections.widget.RecyclerCollectionComponent;
import com.facebook.yoga.YogaEdge;

import java.util.List;

@LayoutSpec
public class ExtCardComponentSpec {

    @OnCreateLayout
    static Component onCreateLayout(ComponentContext c, @Prop List<Collection> collectionList) {

        return RecyclerCollectionComponent.create(c)
                .canMeasureRecycler(true)
                .nestedScrollingEnabled(true)
                .recyclerConfiguration(ListRecyclerConfiguration.create().snapMode(0).orientation(0).build())
                .section(ListSection.create(new SectionContext(c)).dataModels(collectionList).callback(new RefreshCallback() {
                    @Override
                    public void StartRefresh() {

                    }
                }).build()).build();

    }
}
