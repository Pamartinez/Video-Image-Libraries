package com.samsung.android.gallery.app.ui.list.mtp;

import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.samsung.android.gallery.app.ui.abstraction.MvpBaseFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.IBaseListView;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.toolbar.GalleryAppBarLayout;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MtpFragment extends BaseListFragment<IBaseListView, MtpPresenter> implements IBaseListView {
    public MtpFragment() {
        setLocationKey("location://mtp");
    }

    public int getLayoutId() {
        return R.layout.fragment_mtp_layout;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_MTP_VIEW_NORMAL.toString();
    }

    public int getStartPinchDepth() {
        return 0;
    }

    public void onEmptyViewVisibilityChanged(View view) {
        super.onEmptyViewVisibilityChanged(view);
        if (PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_MENU && this.mAppBarLayout != null) {
            View view2 = this.mEmptyView;
            if (view2 == null || view2.getVisibility() != 0) {
                view = getListView();
            }
            this.mAppBarLayout.setScrollEnable(false, view);
        }
    }

    public boolean onListItemLongClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem) {
        return true;
    }

    public void onViewCreated(View view, Bundle bundle) {
        GalleryAppBarLayout galleryAppBarLayout;
        super.onViewCreated(view, bundle);
        if (PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_MENU && (galleryAppBarLayout = this.mAppBarLayout) != null) {
            galleryAppBarLayout.setScrollEnable(false, getListView());
        }
    }

    public void startPostponedEnterTransitionV2() {
        MvpBaseFragment mvpBaseFragment = (MvpBaseFragment) getParentFragment();
        if (mvpBaseFragment != null) {
            mvpBaseFragment.startPostponedEnterTransitionV2();
        } else {
            super.startPostponedEnterTransitionV2();
        }
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportTabLayout() {
        return !PreferenceFeatures.OneUi7x.SUPPORT_BOTTOM_TAB_MENU;
    }

    public LinearLayoutManager createLayoutManager() {
        return new LinearLayoutManager(getContext());
    }

    public MtpViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new MtpViewAdapter(this, getLocationKey());
    }

    public MtpPresenter createPresenter(IBaseListView iBaseListView) {
        return new MtpPresenter(this.mBlackboard, iBaseListView);
    }

    public void addSharedTransition(ListViewHolder listViewHolder, MediaItem mediaItem, int i2, boolean z) {
    }
}
