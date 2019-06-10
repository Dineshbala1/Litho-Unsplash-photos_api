package com.example.litho.picasso;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;

import com.example.litho.picasso.Litho.Components.ExtCardComponent;
import com.example.litho.picasso.Litho.Components.RefreshCallback;
import com.example.litho.picasso.Litho.Components.SearchSection;
import com.example.litho.picasso.Model.SearchShowCaseModel;
import com.example.litho.picasso.Model.UnSplash.Collection;
import com.facebook.litho.Column;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.facebook.litho.sections.Section;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.widget.RecyclerCollectionComponent;
import com.facebook.litho.widget.Text;
import com.facebook.litho.widget.VerticalScroll;
import com.facebook.yoga.YogaAlign;
import com.facebook.yoga.YogaEdge;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.example.litho.picasso.Utilities.GetAssetJsonData;

public class CollectionsFragment extends androidx.fragment.app.Fragment {

    private LithoView _lithoView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        ScrollView scrollView = new ScrollView(this.getContext());
        if (_lithoView != null) {
            return _lithoView;
        }

        ComponentContext contextComponent = new ComponentContext(this.getContext());

        List<Collection> collections = getUnsplashCollections();

        SearchShowCaseModel searchShowCaseModel = new SearchShowCaseModel();
        searchShowCaseModel.Collections = collections;
        searchShowCaseModel.Results = collections;
        searchShowCaseModel.Users = collections;

        final Component horizontalListComponent = RecyclerCollectionComponent.create(contextComponent)
                .disablePTR(true)
                .wrapInView()
                .section(SearchSection.create(new SectionContext(contextComponent)).dataModels(searchShowCaseModel).callback(new RefreshCallback() {
                    @Override
                    public void StartRefresh() {

                    }
                }))
                .verticalFadingEdgeEnabled(false)
                .canMeasureRecycler(false)
                .build();


        /*VerticalScroll.create(contextComponent).childComponent(Column.create(contextComponent)
                .child(ExtCardComponent.create(contextComponent).collectionList(searchShowCaseModel.Collections))).build()*/

        /*_lithoView = LithoView.create(this.getContext(), Column.create(contextComponent)
                .child(ExtCardComponent.create(contextComponent).collectionList(searchShowCaseModel.Collections))
                .child(ExtCardComponent.create(contextComponent).collectionList(searchShowCaseModel.Collections)).build());*/

        Display display = this.getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        Log.e("Width", "" + width);
        Log.e("height", "" + height);

        int totalHeightOfScreenInPixels = height;

        int equalSplitOfHeights = totalHeightOfScreenInPixels / 3;

        int heightInPercentage = equalSplitOfHeights / 100;

        Component com = Column.create(contextComponent)
                .alignItems(YogaAlign.STRETCH)
                .child(Text.create(contextComponent, 0, R.attr.titleTextStyle).typeface(Typeface.DEFAULT_BOLD)
                        .textAlignment(Layout.Alignment.ALIGN_LEFT).textColor(Color.BLACK)
                        .alignSelf(YogaAlign.CENTER).text("Explore").heightPx(heightInPercentage * 10))
                .child(Text.create(contextComponent, 0, R.attr.titleTextStyle).typeface(Typeface.DEFAULT_BOLD)
                        .textAlignment(Layout.Alignment.ALIGN_LEFT).textColor(Color.BLACK)
                        .alignSelf(YogaAlign.CENTER).text("").heightPx(heightInPercentage * 10))
                .child(ExtCardComponent.create(contextComponent).collectionList(searchShowCaseModel.Collections).heightPx(heightInPercentage * 90))
                .child(Text.create(contextComponent, 0, R.attr.titleTextStyle).typeface(Typeface.DEFAULT_BOLD)
                        .textAlignment(Layout.Alignment.ALIGN_LEFT).textColor(Color.BLACK)
                        .alignSelf(YogaAlign.CENTER).text("").heightPx(heightInPercentage * 10))
                .child(Text.create(contextComponent, 0, R.attr.titleTextStyle).typeface(Typeface.DEFAULT_BOLD)
                        .textAlignment(Layout.Alignment.ALIGN_LEFT).textColor(Color.BLACK)
                        .alignSelf(YogaAlign.CENTER).text("Collections").heightPx(heightInPercentage * 10))
                .child(Text.create(contextComponent, 0, R.attr.titleTextStyle).typeface(Typeface.DEFAULT_BOLD)
                        .textAlignment(Layout.Alignment.ALIGN_LEFT).textColor(Color.BLACK)
                        .alignSelf(YogaAlign.CENTER).text("").heightPx(heightInPercentage * 10))
                .child(ExtCardComponent.create(contextComponent).collectionList(searchShowCaseModel.Collections).heightPx(heightInPercentage * 90))
                .child(Text.create(contextComponent, 0, R.attr.titleTextStyle).typeface(Typeface.DEFAULT_BOLD)
                        .textAlignment(Layout.Alignment.ALIGN_LEFT).textColor(Color.BLACK)
                        .alignSelf(YogaAlign.CENTER).text("").heightPx(heightInPercentage * 10))
                .child(Text.create(contextComponent, 0, R.attr.titleTextStyle).typeface(Typeface.DEFAULT_BOLD)
                        .textAlignment(Layout.Alignment.ALIGN_LEFT).textColor(Color.BLACK)
                        .alignSelf(YogaAlign.CENTER).text("Users").heightPx(heightInPercentage * 10))
                .child(Text.create(contextComponent, 0, R.attr.titleTextStyle).typeface(Typeface.DEFAULT_BOLD)
                        .textAlignment(Layout.Alignment.ALIGN_LEFT).textColor(Color.BLACK)
                        .alignSelf(YogaAlign.CENTER).text("").heightPx(heightInPercentage * 10))
                .child(ExtCardComponent.create(contextComponent).collectionList(searchShowCaseModel.Collections).heightPx(heightInPercentage * 90).build()).build();

        _lithoView = LithoView.create(this.getContext(), VerticalScroll.create(contextComponent).incrementalMountEnabled(true).nestedScrollingEnabled(true).fillViewport(true).alignSelf(YogaAlign.STRETCH)
                .childComponent(com).onScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                    @Override
                    public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                        //Log.d(v.getTag().toString(),v.getTag().toString());
                    }
                }).build());

        _lithoView.performIncrementalMount();

        return _lithoView;
    }

    public List<Collection> getUnsplashCollections()
    {
        String collectionsData = GetAssetJsonData(getContext(),"collections.json");

        Type listType = new TypeToken<ArrayList<Collection>>(){}.getType();

        List<Collection> modelObjects = new Gson().fromJson(collectionsData, listType);

        return modelObjects;
    }


}
