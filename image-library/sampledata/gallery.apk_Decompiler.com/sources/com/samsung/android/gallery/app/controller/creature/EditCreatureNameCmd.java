package com.samsung.android.gallery.app.controller.creature;

import C3.C0391a;
import android.os.Bundle;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.SubscriberListener;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Features;
import com.samsung.android.gallery.support.utils.PreferenceCache;
import com.samsung.android.gallery.support.utils.UriBuilder;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class EditCreatureNameCmd extends BaseCommand {
    private MediaItem mHeaderItem;
    private String mLocationKey;
    private final SubscriberListener mRequestPermissionListener = new C0391a(7, this);

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(Object obj, Bundle bundle) {
        onRequestPermissionResult(obj);
    }

    private void onRequestPermissionResult(Object obj) {
        Object[] objArr = (Object[]) obj;
        if (((Integer) objArr[0]).intValue() == 118) {
            getBlackboard().unsubscribe("lifecycle://on_request_permission_result", this.mRequestPermissionListener);
            String[] strArr = (String[]) objArr[1];
            int[] iArr = (int[]) objArr[2];
            for (int i2 = 0; i2 < strArr.length; i2++) {
                if (iArr[i2] == -1) {
                    publishNegativeResult();
                    return;
                }
            }
            showEditCreatureNameDialog();
        }
    }

    private void publishNegativeResult() {
        if (((Boolean) getBlackboard().pop("data://user/faceCluster/isFromFaceMerge", Boolean.FALSE)).booleanValue()) {
            getBlackboard().post("data://user/faceCluster/assignedIdentityInfo", (Object) null);
        }
    }

    private void showEditCreatureNameDialog() {
        getBlackboard().publish("data:///CreatureHeaderItemForEdit", this.mHeaderItem);
        getBlackboard().publish("data:///SearchEditCreatureSource", this.mLocationKey);
        getBlackboard().post("command://MoveURL", new UriBuilder("location://search/EditCreatureName").appendArg("sub", ArgumentsUtil.getArgValue(this.mLocationKey, "sub")).appendArg("prev_sub_category", ArgumentsUtil.getArgValue(this.mLocationKey, "prev_sub_category")).appendArg("term", ArgumentsUtil.getArgValue(this.mLocationKey, "term")).build());
    }

    public boolean isAnalyticsEnabled() {
        return false;
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mLocationKey = objArr[0];
        MediaItem mediaItem = objArr[1];
        this.mHeaderItem = mediaItem;
        if (mediaItem != null) {
            if (!mediaItem.isPeople() || EditCreatureNameWrapper.hasRuntimeContactsPermission(eventContext)) {
                showEditCreatureNameDialog();
                return;
            }
            if (Features.isEnabled(Features.SUPPORT_RECOMMEND_SIMILAR_CONTACT)) {
                PreferenceCache.ConfirmContactPermission.setBoolean(true);
            }
            if (EditCreatureNameWrapper.hasRuntimeContactsPermissionResult(eventContext)) {
                getBlackboard().subscribeOnUi("lifecycle://on_request_permission_result", this.mRequestPermissionListener);
            }
            EditCreatureNameWrapper.requestRuntimePermission(eventContext, 118);
        }
    }
}
