package com.samsung.android.gallery.app.controller.internals;

import K4.a;
import android.content.Intent;
import c0.C0086a;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.service.message.DeleteMsgMgr;
import com.samsung.android.gallery.module.service.support.DeleteInfo;
import com.samsung.android.gallery.module.service.support.DeleteItemInfo;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.ArgumentsUtil;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class DeleteGroupShotCmd extends DeleteCmd {
    private boolean useCheckBox() {
        if (isConfigured(DeleteCmd.DELETE_FROM_SELECTION_VIEW)) {
            return false;
        }
        return !ArgumentsUtil.getArgValue(this.mLocationKey, "disable_check_box", false);
    }

    public void addExtraInfo(Intent intent) {
        super.addExtraInfo(intent);
        intent.putExtra("is_burst_shot_selection", !this.mIsGroupDelete);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_DETAIL_VIEW_FS_DELETE.toString();
    }

    public abstract int getGroupType();

    public String getLocationKey() {
        return ArgumentsUtil.appendUriKey(this.mLocationKey, "viewer", true);
    }

    public String[] getTitleAndDescription(boolean z, boolean z3) {
        DeleteInfo deleteInfo = new DeleteInfo(false, isVirtualAlbum(this.mLocationKey), z, false);
        deleteInfo.calculateCount(this.mItems);
        DeleteItemInfo itemInfo = deleteInfo.getItemInfo();
        if (itemInfo.getTotalCount() != 0) {
            return DeleteMsgMgr.getGroupMessage(getContext(), itemInfo, useCheckBox(), getGroupType(), z3);
        }
        Log.w(this.TAG, "nothing selected");
        return null;
    }

    public void onConfirmed(EventContext eventContext, ArrayList<Object> arrayList) {
        Object obj;
        boolean z;
        int lastOption = getLastOption(arrayList);
        if (lastOption > 0) {
            if (lastOption == 2) {
                z = true;
            } else {
                z = false;
            }
            this.mIsGroupDelete = z;
            preOperateDelete();
            operateDelete();
            return;
        }
        String str = this.TAG;
        StringBuilder o2 = C0086a.o(lastOption, "cancel or unexpected option selected. [", "][");
        if (arrayList != null) {
            obj = Integer.valueOf(arrayList.size());
        } else {
            obj = "null";
        }
        o2.append(obj);
        o2.append("]");
        Log.e(str, o2.toString());
    }

    public void startConfirmDialog() {
        int i2;
        String str = this.TAG;
        Log.d(str, "show group shot confirm dialog [" + this.mUseTrash + "]");
        String[] titleAndDescription = getTitleAndDescription(this.mUseTrash, CloudStateCompat.isNewGalleryAvailable());
        if (titleAndDescription == null) {
            Log.e(this.TAG, "unable to operate. no item selected, exclude null");
            return;
        }
        UriBuilder appendArg = new UriBuilder("data://user/dialog/GroupShotCheckbox").appendArg("title", titleAndDescription[0]).appendArg("check_box_description", titleAndDescription[1]).appendArg("description", titleAndDescription[2]);
        if (this.mUseTrash) {
            i2 = R.string.move_to_trash;
        } else {
            i2 = R.string.delete;
        }
        DataCollectionDelegate.getInitInstance(getHandler()).setRequestData(appendArg.appendArg("option1", i2).appendArg("option1EventId", getEventIdOfDelete(this.mUseTrash)).appendArg("option1ColorRed", !this.mUseTrash).build()).setOnDataCompleteListener(new a(22, this)).start();
    }

    public void preOperateDelete() {
    }
}
