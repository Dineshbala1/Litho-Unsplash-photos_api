package com.example.litho.picasso.Litho.Components;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Layout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.example.litho.picasso.Model.UnSplash.Photo;
import com.facebook.litho.Border;
import com.facebook.litho.ClickEvent;
import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.Row;
import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.annotations.OnViewportChanged;
import com.facebook.litho.widget.Card;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaEdge;

import static com.facebook.yoga.YogaEdge.LEFT;
import static com.facebook.yoga.YogaEdge.RIGHT;

@LayoutSpec
public class PhotoListItemSpec {

    @OnCreateLayout
    static Component onCreateLayout(ComponentContext c, @Prop Photo dataModel, @Prop String imageUrl) {

        Component titleComp = Text.create(c)
                .text(dataModel.getUser().getName().toUpperCase())
                .maxLines(4)
                .textSizeSp(16)
                .justificationMode(1)
                .paddingDip(LEFT, 8)
                .ellipsize(TextUtils.TruncateAt.END)
                .typeface(Typeface.DEFAULT)
                .textAlignment(Layout.Alignment.ALIGN_LEFT)
                .alignSelf(YogaAlign.CENTER)
                .flexShrink(1)
                .wrapInView()
                .breakStrategy(1)
                .clipToBounds(true)
                .clipChildren(true)
                .textColor(Color.BLACK)
                .build();

        return Card.create(c)
                .elevationDip(2)
                .cornerRadiusDip(4)
                .content(
                        ImageComponent.create(c).imageUrl(dataModel.getUrls().getRegular()))
                .clickHandler(ListItem.onClick(c))
                .build();
    }

    @OnEvent(ClickEvent.class)
    static void onClick(
            ComponentContext c,
            @FromEvent View view,
            @Prop Photo dataModel) {

        Toast.makeText(c.getApplicationContext(), "Card clicked"+dataModel.getUser().getName(), Toast.LENGTH_SHORT).show();

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


    /*.child(Row.create(c).heightPercent(10).alignSelf(YogaAlign.STRETCH)
                                        .child(titleComp)
                                        .child(Text.create(c).flexGrow(1).text("").build())
            .child(Text.create(c)
                                                .text(dataModel.getLikes().toString())
            .typeface(Typeface.DEFAULT_BOLD)
                                                .alignSelf(YogaAlign.CENTER)
                                                .paddingDip(RIGHT, 8)
                                                .textSizeSp(10)
                                                .textAlignment(Layout.Alignment.ALIGN_RIGHT))
            .border(Border.create(c)
                                                .widthDip(YogaEdge.ALL, 0)
                                                .color(YogaEdge.ALL, 0xFF000000)
                                                .build()))*/
}
