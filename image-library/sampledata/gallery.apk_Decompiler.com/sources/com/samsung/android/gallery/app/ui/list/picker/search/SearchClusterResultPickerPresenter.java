package com.samsung.android.gallery.app.ui.list.picker.search;

import a6.g;
import android.graphics.drawable.Drawable;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.picker.PickerMenuFactory;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.ISearchClusterResultView;
import com.samsung.android.gallery.app.ui.list.search.pictures.cluster.SearchClusterResult2Presenter;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.app.ui.menu.picker.PickerMenuHandler;
import com.samsung.android.gallery.module.abstraction.LaunchModeType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class SearchClusterResultPickerPresenter<V extends ISearchClusterResultView> extends SearchClusterResult2Presenter<V> {
    protected LaunchModeType mLaunchModeType;

    public SearchClusterResultPickerPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
        LaunchModeType launchModeType = (LaunchModeType) this.mBlackboard.read("data://launch_mode_type");
        this.mLaunchModeType = launchModeType;
        if (launchModeType == LaunchModeType.ACTION_MULTIPLE_PICK) {
            setOnSelectionListener(new g(6, this));
        }
    }

    /* access modifiers changed from: private */
    public boolean onSelectionCompleted(EventContext eventContext, MediaItem[] mediaItemArr) {
        if (mediaItemArr == null || mediaItemArr.length <= 0) {
            return false;
        }
        eventContext.getBlackboard().post("command://MultiplePickerItemsSelection", mediaItemArr);
        return true;
    }

    public boolean checkOptionMenuEnabled() {
        return false;
    }

    public MenuDataBinding createMenuDataBinding() {
        return PickerMenuFactory.create(this.mBlackboard, getLocationKey());
    }

    public MenuHandler createMenuHandler() {
        LaunchModeType launchModeType = this.mLaunchModeType;
        if (launchModeType == LaunchModeType.ACTION_PICK || launchModeType == LaunchModeType.ACTION_COVER_ITEM_PICK) {
            return new PickerMenuHandler();
        }
        return null;
    }

    public int getMaxCount() {
        return PickerUtil.getMaxPickCount(this.mBlackboard);
    }

    public void onListItemClickInternal(int i2, MediaItem mediaItem) {
        LaunchModeType launchModeType = this.mLaunchModeType;
        if (launchModeType == LaunchModeType.ACTION_PICK || launchModeType == LaunchModeType.ACTION_COVER_ITEM_PICK) {
            postAnalyticsLog(AnalyticsEventId.EVENT_SINGLE_PICK_SELECTION_ITEM);
            this.mBlackboard.post("command://SinglePickerItemSelection", mediaItem);
            return;
        }
        super.onListItemClickInternal(i2, mediaItem);
    }

    public void updateToolbar(Toolbar toolbar) {
        if (toolbar != null) {
            toolbar.setTitle((CharSequence) PickerUtil.getUsageTitle(getBlackboard()));
            toolbar.setNavigationIcon((Drawable) null);
        }
    }

    public void initMenu() {
    }
}
