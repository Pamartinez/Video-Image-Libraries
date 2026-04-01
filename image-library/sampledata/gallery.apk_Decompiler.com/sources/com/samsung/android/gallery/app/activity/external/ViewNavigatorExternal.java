package com.samsung.android.gallery.app.activity.external;

import android.os.Bundle;
import com.samsung.android.gallery.app.activity.ViewNavigator;
import com.samsung.android.gallery.app.activity.ViewNavigatorController;
import com.samsung.android.gallery.app.activity.abstraction.IGalleryActivityView;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import com.samsung.android.gallery.support.blackboard.key.CommandKey;
import com.samsung.android.gallery.support.blackboard.key.DataKey;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class ViewNavigatorExternal extends ViewNavigator {
    private ViewNavigatorController mController;

    public ViewNavigatorExternal(Blackboard blackboard, IGalleryActivityView iGalleryActivityView) {
        super(blackboard, iGalleryActivityView);
    }

    /* access modifiers changed from: private */
    public void onRequestCropImage(Object obj, Bundle bundle) {
        if (getViewNavigatorController() != null) {
            getViewNavigatorController().onRequestCropImage(obj, bundle);
        }
    }

    /* access modifiers changed from: private */
    public void onRequestQuickViewItem(Object obj, Bundle bundle) {
        if (getViewNavigatorController() != null) {
            getViewNavigatorController().onRequestQuickViewItem(obj, bundle);
        }
    }

    /* access modifiers changed from: private */
    public void onSharingsDataLoaded(Object obj, Bundle bundle) {
        if (getViewNavigatorController() != null) {
            getViewNavigatorController().onSharingsDataLoaded(obj, bundle);
        }
    }

    /* access modifiers changed from: private */
    public void onTimelineDataLoaded(Object obj, Bundle bundle) {
        if (getViewNavigatorController() != null) {
            getViewNavigatorController().onTimelineDataLoaded(obj, bundle);
        }
    }

    public ViewNavigatorController createDefaultController() {
        return null;
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("lifecycle://on_activity_create", new t(this, 0)).setWorkingCurrent());
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://quickView"), new t(this, 1)).setWorkingCurrent());
        arrayList.add(new SubscriberInfo(DataKey.DATA_CURSOR("location://timeline"), new t(this, 2)).setTriggerPreloadedData());
        arrayList.add(new SubscriberInfo(DataKey.DATA_CURSOR("location://sharing/albums"), new t(this, 3)).setTriggerPreloadedData());
        arrayList.add(new SubscriberInfo(DataKey.DATA_CURSOR("location://sharing/albums/fileList"), new t(this, 3)).setTriggerPreloadedData());
        arrayList.add(new SubscriberInfo(DataKey.DATA_CURSOR("location://sharing/albums/invitations"), new t(this, 3)).setTriggerPreloadedData());
        if (PreferenceFeatures.OneUi41.SHARING_ALBUM_STORAGE_USAGE) {
            arrayList.add(new SubscriberInfo(DataKey.DATA_CURSOR("location://sharing/albums/storageUse"), new t(this, 3)).setTriggerPreloadedData());
        }
        arrayList.add(new SubscriberInfo(CommandKey.DATA_REQUEST("location://cropView"), new t(this, 4)));
    }

    public ViewNavigatorController getViewNavigatorController() {
        IGalleryActivityView iGalleryActivityView;
        if (this.mController == null) {
            synchronized (ViewNavigatorExternal.class) {
                try {
                    if (this.mController == null && (iGalleryActivityView = this.mActivityView) != null) {
                        this.mController = ViewNavigatorExternalControllerFactory.create(this.mBlackboard, iGalleryActivityView);
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return this.mController;
    }

    public void onActivityCreateExternal(Object obj, Bundle bundle) {
        super.onActivityCreate(obj, bundle);
    }

    public void onDestroy() {
        super.onDestroy();
        ViewNavigatorController viewNavigatorController = this.mController;
        if (viewNavigatorController != null) {
            viewNavigatorController.onDestroy();
            this.mController = null;
        }
    }

    public void onActivityCreate(Object obj, Bundle bundle) {
    }
}
