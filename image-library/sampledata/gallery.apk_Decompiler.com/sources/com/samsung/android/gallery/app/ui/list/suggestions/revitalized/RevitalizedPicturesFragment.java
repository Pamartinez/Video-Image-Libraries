package com.samsung.android.gallery.app.ui.list.suggestions.revitalized;

import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsDetail;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listview.PinchLayoutManager;
import com.samsung.android.gallery.widget.listview.pinch.PinchAnimationManager;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public final class RevitalizedPicturesFragment extends BaseListFragment<IRevitalizedView, RevitalizedPicturesPresenter> implements IRevitalizedView {
    /* access modifiers changed from: private */
    public void onGridChanged(int i2, int i7) {
        if (i2 != i7) {
            savePinchDepth(i2);
        }
        P p6 = this.mPresenter;
        if (p6 != null) {
            ((RevitalizedPicturesPresenter) p6).updateMenu();
        }
    }

    public void addSharedTransition(ListViewHolder listViewHolder, MediaItem mediaItem, int i2, boolean z) {
        super.addSharedTransition(listViewHolder, mediaItem, i2, z);
    }

    public void changeView() {
        AnalyticsDetail analyticsDetail;
        int i2;
        if (!getPinchAnimationManager().isAnimating()) {
            int currentViewDepth = ((RevitalizedPicturesPresenter) this.mPresenter).getCurrentViewDepth();
            AnalyticsEventId analyticsEventId = AnalyticsEventId.EVENT_REMASTER_CHANGE_VIEW;
            if (currentViewDepth > 0) {
                analyticsDetail = AnalyticsDetail.REMASTER_CHANGE_TO_SMALL_THUMBNAILS;
            } else {
                analyticsDetail = AnalyticsDetail.REMASTER_CHANGE_TO_LARGE_THUMBNAILS;
            }
            postAnalyticsLog(analyticsEventId, analyticsDetail.toString());
            RevitalizedPicturesPresenter revitalizedPicturesPresenter = (RevitalizedPicturesPresenter) this.mPresenter;
            GalleryListView listView = getListView();
            if (currentViewDepth > 0) {
                i2 = currentViewDepth - 1;
            } else {
                i2 = currentViewDepth + 1;
            }
            revitalizedPicturesPresenter.changeViewDepth(listView, i2);
        }
    }

    public int getLayoutId() {
        return R.layout.fragment_suggestion_pictures_layout;
    }

    public PinchAnimationManager getPinchAnimationManager() {
        return new RevitalizedPicturesPinchAnimationManager((PinchLayoutManager) this.mLayoutManager);
    }

    public int getPreferenceDefault() {
        return 0;
    }

    public PreferenceName getPreferenceName() {
        return PreferenceName.REMASTER_PICTURES_GRID_SIZE;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_SUGGEST_REVITALIZED_PICTURES.toString();
    }

    public int getStartPinchDepth() {
        return loadPinchDepth();
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        getLayoutManager().handleResolutionChange(getContext());
        BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
        if (baseListViewAdapter != null) {
            baseListViewAdapter.notifyItemRangeChanged(0, baseListViewAdapter.getDataCount(), "updateLayout");
        }
    }

    public boolean isAllowAdvancedMouseEvent() {
        return true;
    }

    public int[] loadPinchColumnResource() {
        return getResources().getIntArray(R.array.pinch_remaster_pictures_column_count);
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return true;
    }

    public boolean supportFastScroll() {
        return true;
    }

    public boolean supportShareSheet() {
        return false;
    }

    public boolean supportShrinkTransition() {
        return true;
    }

    public RevitalizedPicturesLayoutManager createLayoutManager() {
        return new RevitalizedPicturesLayoutManager(getContext(), getListView().getColumnCount()) {
            public void setSpanCount(int i2) {
                int spanCount = super.getSpanCount();
                super.setSpanCount(i2);
                RevitalizedPicturesFragment.this.onGridChanged(i2, spanCount);
            }
        };
    }

    public RevitalizedPicturesViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new RevitalizedPicturesViewAdapter(this, getLocationKey());
    }

    public RevitalizedPicturesPresenter createPresenter(IRevitalizedView iRevitalizedView) {
        return new RevitalizedPicturesPresenter(this.mBlackboard, iRevitalizedView);
    }

    public RevitalizedPicturesLayoutManager getLayoutManager() {
        return (RevitalizedPicturesLayoutManager) super.getLayoutManager();
    }
}
