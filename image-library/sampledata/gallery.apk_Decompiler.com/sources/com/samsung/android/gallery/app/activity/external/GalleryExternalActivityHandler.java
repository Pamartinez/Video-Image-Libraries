package com.samsung.android.gallery.app.activity.external;

import android.content.Context;
import android.os.Bundle;
import com.samsung.android.gallery.app.activity.GalleryActivityHandler;
import com.samsung.android.gallery.app.activity.abstraction.IGalleryActivityView;
import com.samsung.android.gallery.module.abstraction.LaunchIntent;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.blackboard.SubscriberInfo;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
class GalleryExternalActivityHandler extends GalleryActivityHandler {
    private LaunchIntent mLaunchIntent;

    public GalleryExternalActivityHandler(Blackboard blackboard, IGalleryActivityView iGalleryActivityView) {
        super(blackboard, iGalleryActivityView);
    }

    /* access modifiers changed from: private */
    public void clearContentViewBackground(Object obj, Bundle bundle) {
        this.mActivityView.clearContentLayoutBgForExternal();
    }

    private boolean isCameraQuickViewOnLockScreen() {
        LaunchIntent launchIntent = this.mLaunchIntent;
        if (launchIntent == null || !launchIntent.isFromCamera() || !this.mLaunchIntent.isFromLockScreen()) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public void restoreContentViewBackground(Object obj, Bundle bundle) {
        this.mActivityView.restoreContentLayoutBgForExternal();
    }

    public void checkGDPR() {
        if (!isCameraQuickViewOnLockScreen()) {
            super.checkGDPR();
        }
    }

    public void createSubscriberList(ArrayList<SubscriberInfo> arrayList) {
        super.createSubscriberList(arrayList);
        arrayList.add(new SubscriberInfo("command:///RestoreContentViewBackground", new a(this, 0)).setWorkingOnUI());
        arrayList.add(new SubscriberInfo("command:///ClearContentViewBackground", new a(this, 1)).setWorkingOnUI());
    }

    public void onActivityCreate(Object obj, Bundle bundle) {
        super.onActivityCreate(obj, bundle);
        this.mLaunchIntent = (LaunchIntent) getBlackboard().read("data://launch_intent");
    }

    public void onActivityDestroy(Object obj, Bundle bundle) {
        super.onActivityDestroy(obj, bundle);
        this.mLaunchIntent = null;
    }

    public void resumeServiceIfPresent(Context context) {
    }

    public void onActivityPauseBg(Object obj, Bundle bundle) {
    }
}
