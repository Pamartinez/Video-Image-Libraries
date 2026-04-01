package com.samsung.android.gallery.app.ui.list.stories.hiderule;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListFragment;
import com.samsung.android.gallery.app.ui.list.abstraction.BaseListViewAdapter;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.HideRulePresenter;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.abstraction.IHideRuleView;
import com.samsung.android.gallery.app.ui.list.stories.hiderule.v1.HideRuleAdapter;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsScreenId;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.widget.listview.GalleryListView;
import com.samsung.android.gallery.widget.listviewholder.ListViewHolder;
import com.samsung.android.gallery.widget.utils.ViewUtils;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HideRuleFragment<V extends IHideRuleView, P extends HideRulePresenter> extends BaseListFragment<V, P> implements IHideRuleView {
    public HideRuleFragment() {
        setLocationKey("location://stories/hideRule");
        Log.d(this.TAG, "support hide pets", Boolean.valueOf(Features.isEnabled(Features.SUPPORT_HIDE_RULE_PETS)));
    }

    private void updateMainLayoutPaddingHorizontal() {
        ViewUtils.setMainLayoutFlexibleSideSpacing((ViewGroup) getListView().getParent());
    }

    public void adjustContentAreaMargin(View view, WindowInsets windowInsets, boolean z) {
        super.adjustContentAreaMargin(view, windowInsets, z);
    }

    public BaseListViewAdapter createListViewAdapter(GalleryListView galleryListView) {
        return new HideRuleAdapter(this, getLocationKey());
    }

    public int getLayoutId() {
        return R.layout.fragment_hide_rule_layout;
    }

    public String getScreenId() {
        return AnalyticsScreenId.SCREEN_EVENT_HIDE_RULE.toString();
    }

    public int getStartPinchDepth() {
        return 0;
    }

    public void handleResolutionChange(int i2) {
        super.handleResolutionChange(i2);
        if (this.mListAdapter != null) {
            ((GridLayoutManager) getLayoutManager()).setSpanCount(this.mListAdapter.getGridSize());
            BaseListViewAdapter baseListViewAdapter = this.mListAdapter;
            baseListViewAdapter.notifyItemRangeChanged(0, baseListViewAdapter.getDataCount(), "onConfigurationChanged");
        }
        updateMainLayoutPaddingHorizontal();
    }

    public void launchDatePicker() {
        ((HideRulePresenter) this.mPresenter).launchDatePicker();
    }

    public void launchHideSceneSelection(int i2, MediaItem mediaItem) {
        ((HideRulePresenter) this.mPresenter).launchHideSceneSelection(i2, mediaItem);
    }

    public int[] loadPinchColumnResource() {
        return new int[]{1};
    }

    public void onListItemClick(ListViewHolder listViewHolder, int i2, MediaItem mediaItem, int i7) {
        super.onListItemClick(listViewHolder, i2, mediaItem, i7);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        updateMainLayoutPaddingHorizontal();
    }

    public void removeDate(int i2, MediaItem mediaItem) {
        ((HideRulePresenter) this.mPresenter).removeDate(i2, mediaItem);
    }

    public boolean supportEnterDefaultTransition() {
        return true;
    }

    public boolean supportExitDefaultTransition() {
        return true;
    }

    public boolean supportPinchShrink() {
        return false;
    }

    public boolean supportSelection() {
        return false;
    }

    public LinearLayoutManager createLayoutManager() {
        return new GridLayoutManager(getContext(), getMaxColumnSize());
    }

    public HideRulePresenter createPresenter(IHideRuleView iHideRuleView) {
        return new HideRulePresenter(this.mBlackboard, iHideRuleView);
    }

    public void addSharedTransition(ListViewHolder listViewHolder, MediaItem mediaItem, int i2, boolean z) {
    }
}
