package com.samsung.android.gallery.app.controller.viewer;

import U3.a;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.StorageUtil;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SaveGroupShotCmd extends BaseCommand {
    private MediaItem[] mItems = null;

    private boolean isSelectAll(int i2) {
        if (this.mItems.length == i2) {
            return true;
        }
        return false;
    }

    /* access modifiers changed from: private */
    public void onConfirmed(EventContext eventContext, ArrayList<Object> arrayList) {
        int i2;
        String str;
        boolean z = false;
        if (arrayList != null) {
            try {
                i2 = ((Integer) arrayList.get(0)).intValue();
            } catch (ClassCastException e) {
                Log.e(this.TAG, "unexpected result delivered." + e.getMessage());
                return;
            }
        } else {
            i2 = -1;
        }
        if (i2 == 2) {
            z = true;
        }
        if (i2 != 1) {
            if (!z) {
                Log.e(this.TAG, "cancel or unexpected option selected.");
                postAnalyticsLog(AnalyticsEventId.EVENT_DETAIL_VIEW_SAVE_BURSTSHOT_IMAGE_CANCLE);
                return;
            }
        }
        operateSave(eventContext, z);
        getBlackboard().postEvent(EventMessage.obtain(1003));
        AnalyticsEventId analyticsEventId = AnalyticsEventId.EVENT_DETAIL_VIEW_SAVE_BURSTSHOT_IMAGE_OK;
        if (z) {
            str = "1";
        } else {
            str = "0";
        }
        postAnalyticsLog(analyticsEventId, str);
    }

    private void operateSave(EventContext eventContext, boolean z) {
        getBlackboard().publish("data://user/selected", this.mItems);
        getBlackboard().publish("data://user/bestItem", eventContext.getBestItem());
        Intent intent = new Intent();
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.SaveGroupShotService");
        intent.setAction("com.samsung.android.gallery.app.service.START_SERVICE");
        intent.putExtra("blackboard_name", getBlackboard().getName());
        intent.putExtra("location_key", eventContext.getLocationKey());
        intent.putExtra("is_delete_remain", z);
        intent.putExtra("is_contain_video", isContainVideo());
        getContext().startService(intent);
    }

    private void showSaveGroupShotDialog(EventContext eventContext, int i2) {
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/dialog/GroupShotCheckbox").appendArg("title", getTitle(this.mItems.length)).appendArg("check_box_description", getDescription(isSelectAll(i2))).appendArg("option1", (int) R.string.save).build()).setOnDataCompleteListener(new a(16, this)).start();
    }

    public String getDescription(boolean z) {
        if (z) {
            return null;
        }
        return getContext().getString(R.string.delete_all_the_unselected_pictures);
    }

    public abstract String getShotName(Context context);

    public String getTitle(int i2) {
        return getContext().getResources().getQuantityString(R.plurals.save_selected_picture_plural, i2, new Object[]{Integer.valueOf(i2), getShotName(getContext())});
    }

    public abstract boolean isContainVideo();

    public void onExecute(EventContext eventContext, Object... objArr) {
        this.mItems = objArr[0];
        int intValue = objArr[1].intValue();
        MediaItem[] mediaItemArr = this.mItems;
        if (mediaItemArr == null || mediaItemArr.length == 0) {
            Log.e(this.TAG, "unable to operate. no item selected.");
        } else if (StorageUtil.checkLowStorage(true)) {
            Log.e(this.TAG, "unable to operate. low storage.");
            Toast.makeText(getContext(), R.string.unable_to_save, 0).show();
        } else {
            showSaveGroupShotDialog(eventContext, intValue);
        }
    }
}
