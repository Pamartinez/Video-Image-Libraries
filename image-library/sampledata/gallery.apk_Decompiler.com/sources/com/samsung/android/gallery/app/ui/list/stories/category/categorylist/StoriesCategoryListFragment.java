package com.samsung.android.gallery.app.ui.list.stories.category.categorylist;

import android.view.View;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.pinch.IStoriesPinchView;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewFragment;
import com.samsung.android.gallery.app.ui.list.stories.pinch.StoriesPinchViewPresenter;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.widget.abstraction.SharedTransition;
import com.samsung.android.gallery.widget.listview.GalleryListView;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoriesCategoryListFragment<V extends IStoriesPinchView, P extends StoriesPinchViewPresenter> extends StoriesPinchViewFragment<V, P> implements IStoriesPinchView {
    public BaseListViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new StoriesPinchViewAdapter(this, getLocationKey(), (View) null);
    }

    public String getFragmentTag(String str) {
        return "StoriesCategoryListFragment";
    }

    public String getScreenId() {
        String locationKey = getLocationKey();
        locationKey.getClass();
        if (!locationKey.equals("location://stories/category/creation")) {
            if (!locationKey.equals("location://stories/category/trip")) {
                return super.getScreenId();
            }
            if (isSelectionMode()) {
                return AnalyticsScreenId.SCREEN_STORY_CATEGORY_TRIP_VIEW_SELECTION.toString();
            }
            return AnalyticsScreenId.SCREEN_STORY_CATEGORY_TRIP_VIEW_NORMAL.toString();
        } else if (isSelectionMode()) {
            return AnalyticsScreenId.SCREEN_STORY_CATEGORY_CREATION_VIEW_SELECTION.toString();
        } else {
            return AnalyticsScreenId.SCREEN_STORY_CATEGORY_CREATION_VIEW_NORMAL.toString();
        }
    }

    public void setScreenMode() {
        enterFullListScreen(false);
    }

    public void startShrinkAnimation() {
        SharedTransition.startPostponedEnterTransition(this, this.mBlackboard);
        startDecoAnimationForReturn();
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return true;
    }

    public boolean supportPinView() {
        return false;
    }

    public boolean supportTabLayout() {
        return false;
    }

    public StoriesPinchViewPresenter createPresenter(IStoriesPinchView iStoriesPinchView) {
        return new StoriesCategoryListPresenter(getBlackboard(), iStoriesPinchView);
    }
}
