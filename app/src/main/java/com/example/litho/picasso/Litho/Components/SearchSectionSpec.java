
package com.example.litho.picasso.Litho.Components;


import android.graphics.Color;
import android.widget.Toast;

import com.example.litho.picasso.Model.SearchShowCaseModel;
import com.example.litho.picasso.Model.UnSplash.Collection;
import com.facebook.litho.Column;
import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.sections.Children;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.annotations.GroupSectionSpec;
import com.facebook.litho.sections.annotations.OnCreateChildren;
import com.facebook.litho.sections.annotations.OnRefresh;
import com.facebook.litho.sections.annotations.OnViewportChanged;
import com.facebook.litho.sections.common.DataDiffSection;
import com.facebook.litho.sections.common.RenderEvent;
import com.facebook.litho.sections.common.SingleComponentSection;
import com.facebook.litho.sections.widget.GridRecyclerConfiguration;
import com.facebook.litho.sections.widget.RecyclerCollectionComponent;
import com.facebook.litho.widget.ComponentRenderInfo;
import com.facebook.litho.widget.RenderInfo;
import com.facebook.litho.widget.Text;

import java.util.List;

@GroupSectionSpec
public class SearchSectionSpec {


    @OnCreateChildren
    static Children onCreateChildren(final SectionContext c, @Prop SearchShowCaseModel dataModels, @Prop RefreshCallback callback) {
        return Children.create()
                .child(
                        SingleComponentSection.create(c)
                                .component(
                                        Column.create(c)
                                                .child(ExtCardComponent.create(c).collectionList(dataModels.Collections)).build()))
                .build();
    }

    @OnEvent(RenderEvent.class)
    static RenderInfo onRender(
            SectionContext c,
            @FromEvent Collection model) {
        return ComponentRenderInfo.create()
                .component(
                        Text.create(c)
                                .text(model.getTitle())
                                .build())
                .build();
    }

    @OnRefresh
    static void onRefresh(SectionContext c) {
        // Handle your refresh request
        Toast.makeText(c.getAndroidContext(), "On Refresh called", Toast.LENGTH_SHORT).show();
    }

    @OnViewportChanged
    static void onViewportChanged(
            SectionContext c,
            int firstVisiblePosition,
            int lastVisiblePosition,
            int totalCount,
            int firstFullyVisibleIndex,
            int lastFullyVisibleIndex,
            @Prop RefreshCallback callback
    ) {

        if (totalCount - 1 == lastFullyVisibleIndex) {

            Toast.makeText(c.getAndroidContext(), "End of list", Toast.LENGTH_SHORT).show();
            if (callback != null) {
                callback.StartRefresh();
            }
        }
    }
}
