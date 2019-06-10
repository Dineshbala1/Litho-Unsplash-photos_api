package com.example.litho.picasso.Litho.Components;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Debug;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.litho.picasso.Model.UnSplash.Collection;
import com.example.litho.picasso.Model.UnSplash.CoverPhoto;
import com.example.litho.picasso.Model.UnSplash.Photo;
import com.example.litho.picasso.Model.UnSplash.Position;
import com.example.litho.picasso.R;
import com.facebook.litho.Border;
import com.facebook.litho.ClickEvent;
import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.EventHandler;
import com.facebook.litho.Row;
import com.facebook.litho.Size;
import com.facebook.litho.TouchEvent;
import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.LayoutSpec;
import com.facebook.litho.annotations.OnCreateLayout;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.widget.Card;
import com.facebook.litho.widget.SolidColor;
import com.facebook.litho.widget.Text;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaEdge;
import com.facebook.yoga.YogaJustify;
import com.example.litho.picasso.Model.DataModel;
import com.facebook.yoga.YogaPositionType;
import com.facebook.yoga.YogaWrap;
import com.google.android.material.resources.TextAppearance;

import static com.facebook.yoga.YogaEdge.ALL;
import static com.facebook.yoga.YogaEdge.BOTTOM;
import static com.facebook.yoga.YogaEdge.LEFT;
import static com.facebook.yoga.YogaEdge.RIGHT;
import static com.facebook.yoga.YogaEdge.START;
import static com.facebook.yoga.YogaEdge.TOP;

@LayoutSpec
public class ListItemSpec {

    private final static String ImageUrl1 = "https://picsum.photos/id/1020/250/250";
    private final static String ImageUrl2 = "https://picsum.photos/id/1027/250/250";
    private final static String ImageUrl3 = "https://picsum.photos/id/1028/250/250";
    private final static String ImageUrl4 = "https://picsum.photos/id/1029/250/250";

    @OnCreateLayout
    static Component onCreateLayout(ComponentContext c, @Prop Collection dataModel, @Prop String imageUrl) {

        Component titleComp = Text.create(c,0, R.attr.titleTextStyle)
                .text(dataModel.getTitle().toUpperCase())
                .maxLines(4)
                .textSizeSp(12)
                .justificationMode(1)
                .paddingDip(LEFT,8)
                .ellipsize(TextUtils.TruncateAt.END)
                .typeface(Typeface.DEFAULT_BOLD)
                .textAlignment(Layout.Alignment.ALIGN_LEFT)
                .alignSelf(YogaAlign.CENTER)
                .flexShrink(1)
                .wrapInView()
                .breakStrategy(1)
                .clipToBounds(true)
                .clipChildren(true)
                .textColor(Color.BLACK)
                .build();

        Component descComp = Text.create(c)
                .text(dataModel.getDescription())
                .maxLines(4)
                .ellipsize(TextUtils.TruncateAt.END)
                .textSizeSp(17)
                .paddingDip(YogaEdge.BOTTOM, 8)
                .marginDip(YogaEdge.VERTICAL, 16)
                .marginDip(YogaEdge.HORIZONTAL, 8)
                .build();

        Component backgroundColor = SolidColor.create(c).heightDip(240).widthDip(320).paddingDip(ALL, 10).clipChildren(true).flexGrow(1).color(Color.parseColor("#D9000000")).alpha(0.5f).positionType(YogaPositionType.ABSOLUTE).alignSelf(YogaAlign.STRETCH).build();

        Size outputSize = new Size();

        backgroundColor.measure(c, 0, 0, outputSize);

        Log.d("Size of ", String.valueOf(outputSize.height) + String.valueOf(outputSize.width));

        return Card.create(c)
                .elevationDip(2)
                .cornerRadiusDip(4)
                .widthDip(300)
                .content(
                        Column.create(c)
                                .child(Row.create(c).flex(1).child(ImageComponent.create(c).imageUrl(dataModel.getPreviewPhoto().get(0).getUrls().getRegular()).heightPercent(85).widthPercent(70))
                                .child(Column.create(c).heightPercent(85).widthPercent(30).paddingDip(START,1)
                                        .child(ImageComponent.create(c).imageUrl(dataModel.getPreviewPhoto().get(1).getUrls().getRegular()))
                                        .child(ImageComponent.create(c).imageUrl(dataModel.getPreviewPhoto().get(2).getUrls().getRegular()))
                                        .child(ImageComponent.create(c).imageUrl(dataModel.getPreviewPhoto().get(3).getUrls().getRegular()))))
                                .child(Row.create(c).heightPercent(15).alignSelf(YogaAlign.STRETCH)
                                        .child(titleComp)
                                        .child(Text.create(c).flexGrow(1).text("").build())
                                        .child(Text.create(c)
                                                .text(dataModel.getCoverPhoto().getLikes().toString())
                                                .typeface(Typeface.DEFAULT_BOLD)
                                                .alignSelf(YogaAlign.CENTER)
                                                .paddingDip(RIGHT, 8)
                                                .textSizeSp(10)
                                                .textAlignment(Layout.Alignment.ALIGN_RIGHT))
                                        .border(Border.create(c)
                                        .widthDip(YogaEdge.ALL, 0)
                                        .color(YogaEdge.ALL, 0xFF000000)
                                        .build()))
                )
                .clickHandler(ListItem.onClick(c))
                .build();
    }

    @OnEvent(ClickEvent.class)
    static void onClick(
            ComponentContext c,
            @FromEvent View view,
            @Prop Collection dataModel) {
        // Handle click here.

        Toast.makeText(c.getApplicationContext(), "Card clicked" + dataModel.getTitle(), Toast.LENGTH_SHORT).show();

        // Make navigation from here

    }
}

