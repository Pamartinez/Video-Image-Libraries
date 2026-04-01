package com.samsung.android.gallery.app.ui.list.picker.pictures;

import U3.a;
import androidx.appcompat.widget.Toolbar;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.ui.list.picker.PickerMenuFactory;
import com.samsung.android.gallery.app.ui.list.pictures.IPicturesView;
import com.samsung.android.gallery.app.ui.list.pictures.PicturesPresenter;
import com.samsung.android.gallery.app.ui.menu.MenuDataBinding;
import com.samsung.android.gallery.app.ui.menu.MenuHandler;
import com.samsung.android.gallery.app.ui.menu.picker.PickerMenuHandler;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.module.abstraction.LaunchModeType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.utils.PickerUtil;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class PicturesPickerPresenter<V extends IPicturesView> extends PicturesPresenter<V> {
    protected LaunchModeType mLaunchModeType;

    public PicturesPickerPresenter(Blackboard blackboard, V v) {
        super(blackboard, v);
        LaunchModeType launchModeType = (LaunchModeType) this.mBlackboard.read("data://launch_mode_type");
        this.mLaunchModeType = launchModeType;
        if (launchModeType == LaunchModeType.ACTION_MULTIPLE_PICK) {
            setOnSelectionListener(new a(29, this));
        }
    }

    public MenuDataBinding createMenuDataBinding() {
        return PickerMenuFactory.create(this.mBlackboard, getLocationKey());
    }

    public MenuHandler createMenuHandler() {
        LaunchModeType launchModeType = this.mLaunchModeType;
        if (launchModeType == LaunchModeType.ACTION_PICK || launchModeType == LaunchModeType.ACTION_COVER_ITEM_PICK) {
            return new PickerMenuHandler();
        }
        return super.createMenuHandler();
    }

    public LaunchModeType getLaunchMode() {
        return this.mLaunchModeType;
    }

    public int getMaxCount() {
        return PickerUtil.getMaxPickCount(this.mBlackboard);
    }

    public boolean handleEvent(EventMessage eventMessage) {
        int i2 = eventMessage.what;
        if (i2 == 2013) {
            forceReloadData();
            return true;
        } else if (i2 != 2014) {
            return super.handleEvent(eventMessage);
        } else {
            ((IPicturesView) this.mView).resetScreenShotFilter();
            return true;
        }
    }

    public boolean isAllowItemClick() {
        if (!PickerUtil.isSinglePickLaunchMode(this.mBlackboard)) {
            return super.isAllowItemClick();
        }
        return setInputBlock(this.TAG + "_isAllowItemClick", 500);
    }

    public boolean isCoverItemPick() {
        if (this.mLaunchModeType == LaunchModeType.ACTION_COVER_ITEM_PICK) {
            return true;
        }
        return false;
    }

    public boolean isRequireCrop() {
        LaunchIntent launchIntent = (LaunchIntent) this.mBlackboard.read("data://launch_intent");
        if (launchIntent == null || !launchIntent.isRequireCrop()) {
            return false;
        }
        return true;
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

    public boolean onSelectionCompleted(EventContext eventContext, MediaItem[] mediaItemArr) {
        if (mediaItemArr == null || mediaItemArr.length <= 0) {
            return false;
        }
        eventContext.getBlackboard().post("command://MultiplePickerItemsSelection", mediaItemArr);
        return true;
    }

    public void updateToolbar(Toolbar toolbar) {
        toolbar.setTitle((CharSequence) PickerUtil.getUsageTitle(getBlackboard()));
        setNavigationUpButton(toolbar);
    }
}
