package com.example.litho.picasso.Litho.Components;

import com.example.litho.picasso.Model.UnSplash.Photo;
import com.example.litho.picasso.PhotoModel;
import com.example.litho.picasso.Services.PhotoDataService;
import com.facebook.litho.Column;
import com.facebook.litho.StateValue;
import com.facebook.litho.annotations.FromEvent;
import com.facebook.litho.annotations.OnCreateInitialState;
import com.facebook.litho.annotations.OnEvent;
import com.facebook.litho.annotations.OnUpdateState;
import com.facebook.litho.annotations.Param;
import com.facebook.litho.annotations.Prop;
import com.facebook.litho.annotations.State;
import com.facebook.litho.sections.Children;
import com.facebook.litho.sections.FocusType;
import com.facebook.litho.sections.LoadingEvent;
import com.facebook.litho.sections.SectionContext;
import com.facebook.litho.sections.SectionLifecycle;
import com.facebook.litho.sections.annotations.GroupSectionSpec;
import com.facebook.litho.sections.annotations.OnBindService;
import com.facebook.litho.sections.annotations.OnCreateChildren;
import com.facebook.litho.sections.annotations.OnCreateService;
import com.facebook.litho.sections.annotations.OnUnbindService;
import com.facebook.litho.sections.annotations.OnViewportChanged;
import com.facebook.litho.sections.annotations.*;
import com.facebook.litho.sections.common.SingleComponentSection;
import com.facebook.litho.widget.Progress;
import com.facebook.litho.widget.SmoothScrollAlignmentType;
import com.facebook.yoga.YogaAlign;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@GroupSectionSpec
public class PhotoListSectionSpec {

    private static final String TAG = "PhotoListSectionSpec";

    @OnCreateInitialState
    static void createInitialState(
            final SectionContext c,
            final StateValue<List<Photo>> photos,
            StateValue<Integer> pageNumber,
            StateValue<Integer> perPageItem,
            StateValue<Boolean> isFetching) {

        pageNumber.set(1);
        perPageItem.set(25);
        photos.set(new ArrayList<Photo>());
        isFetching.set(false);
    }

    @OnCreateChildren
    public static Children onCreateChildren(final SectionContext c, @State List<Photo> photos) {
        Children.Builder builder = Children.create();

        if (photos != null && photos.size() > 0) {

            for (int i = 0; i < photos.size(); i++) {
                builder.child(
                        SingleComponentSection.create(c)
                                .key(String.valueOf(i))
                                .component(PhotoListItem.create(c).dataModel(photos.get(i)).imageUrl("").build()));
            }

            if (photos.size() <= 25) {
                SectionLifecycle.requestFocus(c, 0);
            }
        }

        builder.child(
                SingleComponentSection.create(c)
                        .component(Column.create(c)
                                .child(
                                        Progress.create(c)
                                                .widthDip(40)
                                                .heightDip(40)
                                                .alignSelf(YogaAlign.CENTER)
                                                .build()
                                ).alignSelf(YogaAlign.CENTER)
                                .build())
                        .build()
        );

        return builder.build();
    }

    @OnCreateService
    public static PhotoDataService onCreateService(
            final SectionContext c,
            @State List<Photo> photos,
            @State int pageNumber,
            @State int perPageItem
    ) {
        return new PhotoDataService();
    }

    @OnBindService
    static void onBindService(final SectionContext c, final PhotoDataService service) {
        service.RegisterPhotoDataEventHandler(PhotoListSection.onDataLoaded(c));
        if (!service.getInitialized()) {
            service.getPhotos(1, 25, null);
        }
    }

    @OnUnbindService
    static void onUnbindService(final SectionContext c, final PhotoDataService service) {
        service.UnRegisterPhotoDataEventHandler();
    }

    @OnEvent(PhotoModel.class)
    static void onDataLoaded(final SectionContext c, @FromEvent List<Photo> photos) {
        PhotoListSection.updateData(c, photos);
        PhotoListSection.setFetching(c, false);
        SectionLifecycle.dispatchLoadingEvent(c, false, LoadingEvent.LoadingState.SUCCEEDED, null);
    }

    @OnUpdateState
    static void updateData(
            final StateValue<List<Photo>> photos,
            final StateValue<Integer> pageNumber,
            @Param List<Photo> newphotos
    ) {
        if (pageNumber.get() == 0) {
            photos.set(newphotos);
        } else {
            List<Photo> photos1 = new ArrayList<>();
            photos1.addAll(photos.get());
            photos1.addAll(newphotos);
            photos.set(photos1);
        }
    }

    @OnRefresh
    static void onRefresh(
            SectionContext c,
            PhotoDataService service,
            @State List<Photo> photos,
            @State int pageNumber,
            @State int perPageItem
    ) {
        /*ListSection.updateStartParam(c, 0);
        service.refetch(0, 15);*/
    }

    @OnUpdateState
    static void setFetching(final StateValue<Boolean> isFetching, @Param boolean fetch) {
        isFetching.set(fetch);
    }

    @OnUpdateState
    static void updateStartParam(final StateValue<Integer> pageNumber, @Param int newStart) {
        pageNumber.set(newStart);
    }


    @OnViewportChanged
    static void onViewportChanged(
            SectionContext c,
            int firstVisiblePosition,
            int lastVisiblePosition,
            int totalCount,
            int firstFullyVisibleIndex,
            int lastFullyVisibleIndex,
            PhotoDataService service,
            @State List<Photo> photos,
            @State int pageNumber,
            @State int perPageItem,
            @State boolean isFetching
    ) {

        if (totalCount - 1 == lastFullyVisibleIndex) {

            //Toast.makeText(c.getAndroidContext(), "End of list", Toast.LENGTH_SHORT).show();
            if (!isFetching) {
                PhotoListSection.setFetching(c, true);
                PhotoListSection.updateStartParam(c, pageNumber+1);
                service.FetchPhotos(pageNumber+1, perPageItem);
            }
        }
    }
}
