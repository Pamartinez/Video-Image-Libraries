package com.samsung.android.gallery.app.ui.list.stories.category.categorylist;

import Ad.C0720a;
import U9.b;
import V8.a;
import android.view.ViewGroup;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.category.categorylist.IStoriesTripListView;
import com.samsung.android.gallery.app.ui.list.stories.category.categorylist.StoriesTripListPresenter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemStory;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.TimeUtil;
import com.samsung.android.gallery.widget.abstraction.TransitionInfo;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesTripListFragment<V extends IStoriesTripListView, P extends StoriesTripListPresenter<V>> extends StoriesCategoryListFragment<V, P> implements IStoriesTripListView {
    private YearListView mYearListView;

    private ArrayList<Integer> getYears() {
        ArrayList<MediaItem> allData = this.mMediaData.getAllData();
        if (allData == null) {
            return new ArrayList<>();
        }
        ArrayList<Integer> arrayList = (ArrayList) allData.stream().map(new a(12)).distinct().sorted(Comparator.reverseOrder()).collect(Collectors.toCollection(new C0720a(1)));
        arrayList.add(0, 0);
        return arrayList;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onDataChangedOnUi$1(YearListView yearListView) {
        yearListView.update(getYears());
    }

    private void moveToYearList() {
        MediaItem mediaItem;
        YearListView yearListView = this.mYearListView;
        if (yearListView != null && !yearListView.isShowAll()) {
            TransitionInfo transitionInfo = (TransitionInfo) this.mBlackboard.read("data://shared_original_bitmap");
            if (transitionInfo != null) {
                mediaItem = transitionInfo.item;
            } else {
                mediaItem = null;
            }
            this.mYearListView.setFocus(TimeUtil.getYearInt(MediaItemStory.getStoryStartTime(mediaItem)));
        }
    }

    /* access modifiers changed from: private */
    public void onSelectYear(Integer num) {
        AnalyticsEventId analyticsEventId;
        StoriesTripListAdapter adapter = getAdapter();
        if (adapter != null) {
            adapter.setSelectedYear(num.intValue());
            adapter.notifyDataSetChanged();
        }
        String analyticsScreenId = AnalyticsScreenId.SCREEN_STORY_CATEGORY_TRIP_VIEW_NORMAL.toString();
        if (isShowAll()) {
            analyticsEventId = AnalyticsEventId.EVENT_STORY_CATEGORY_TRIP_FILTER_SELECT_ALL;
        } else {
            analyticsEventId = AnalyticsEventId.EVENT_STORY_CATEGORY_TRIP_FILTER_SELECT_YEAR;
        }
        postAnalyticsLog(analyticsScreenId, analyticsEventId);
    }

    public void addHeaderSubView(ViewGroup viewGroup) {
        this.mYearListView = new YearListView(viewGroup, getYears(), new b(15, this));
    }

    public BaseListViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new StoriesTripListAdapter(this, getLocationKey(), createHeaderView());
    }

    public String getFragmentTag(String str) {
        return "StoriesTripListFragment";
    }

    public int getHeaderLayoutId() {
        return R.layout.stories_category_trip_header_layout;
    }

    public int getSelectedCountForToolbar(boolean z) {
        return getSelectedItemCount();
    }

    public int getTotalSelectableCount() {
        if (getAdapter() != null) {
            return getAdapter().getItemCount() - 1;
        }
        return super.getTotalSelectableCount();
    }

    public void handleDensityChange(int i2) {
        super.handleDensityChange(i2);
        Optional.ofNullable(this.mYearListView).ifPresent(new b(0));
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        Optional.ofNullable(this.mYearListView).ifPresent(new b(1));
    }

    public boolean isShowAll() {
        YearListView yearListView = this.mYearListView;
        if (yearListView != null) {
            return yearListView.isShowAll();
        }
        return true;
    }

    public void onDataChangedOnUi() {
        super.onDataChangedOnUi();
        if (getAdapter() != null) {
            getAdapter().update();
            Optional.ofNullable(this.mYearListView).ifPresent(new d(0, this));
        }
    }

    public void onSelectionModeChanged(boolean z) {
        Optional.ofNullable(this.mYearListView).ifPresent(new c(z));
    }

    public void resetYearFilter() {
        Optional.ofNullable(this.mYearListView).ifPresent(new b(2));
    }

    public void startSimpleAutoScroller(int i2) {
        moveToYearList();
        super.startSimpleAutoScroller(i2);
    }

    public StoriesTripListPresenter createPresenter(IStoriesTripListView iStoriesTripListView) {
        return new StoriesTripListPresenter(this.mBlackboard, iStoriesTripListView);
    }

    public StoriesTripListAdapter getAdapter() {
        return (StoriesTripListAdapter) this.mListAdapter;
    }
}
