package com.example.litho.picasso.Litho.Components;


import android.widget.Toast;

import com.example.litho.picasso.Model.UnSplash.Collection;
import com.example.litho.picasso.Model.UnSplash.Photo;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.PropDefault;
import com.facebook.litho.annotations.State;
import com.facebook.litho.sections.Children;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.annotations.GroupSectionSpec;
import com.facebook.litho.sections.annotations.OnCreateChildren;
import com.facebook.litho.sections.annotations.OnRefresh;
import com.facebook.litho.sections.annotations.OnViewportChanged;
import com.facebook.litho.sections.common.SingleComponentSection;
import com.example.litho.picasso.Model.DataModel;
import com.facebook.litho.widget.Text;

import java.util.List;

@GroupSectionSpec
public class ListSectionSpec {


    @OnCreateChildren
    static Children onCreateChildren(final SectionContext c, @Prop List<Collection> dataModels, @Prop RefreshCallback callback) {
        Children.Builder builder = Children.create();

        if (dataModels != null && dataModels.size() > 0) {

            for (int i = 0; i < dataModels.size(); i++) {
                builder.child(
                        SingleComponentSection.create(c)
                                .key(String.valueOf(i))
                                .component(ListItem.create(c).dataModel(dataModels.get(i)).imageUrl("").build()));
            }
        }

        return builder.build();
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