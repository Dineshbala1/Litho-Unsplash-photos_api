package com.example.litho.picasso;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.litho.picasso.Litho.Components.PhotoListSection;
import com.facebook.litho.Component;
import com.facebook.litho.ComponentContext;
import com.facebook.litho.LithoView;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.widget.GridRecyclerConfiguration;
import com.facebook.litho.sections.widget.RecyclerCollectionComponent;
import com.facebook.litho.sections.widget.StaggeredGridRecyclerConfiguration;

public class PhotosFragment extends Fragment {


    private LithoView _lithoView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setRetainInstance(true);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (_lithoView != null) {
            return _lithoView;
        }

        int noofColumns = 2;

        if (getResources().getBoolean(R.bool.IsTablet)) {
            noofColumns = 3;
        }

        StaggeredGridRecyclerConfiguration gridRecyclerConfiguration = StaggeredGridRecyclerConfiguration.create().numSpans(noofColumns).build();

        ComponentContext contextComponent = new ComponentContext(this.getContext());

        final Component component =
                RecyclerCollectionComponent.create(contextComponent)
                        .disablePTR(false)
                        .recyclerConfiguration(gridRecyclerConfiguration)
                        .section(PhotoListSection.create(new SectionContext(contextComponent))).verticalFadingEdgeEnabled(false).canMeasureRecycler(true).build();

        _lithoView = LithoView.create(this.getContext(), component);

        return _lithoView;
    }
}
