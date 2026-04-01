package com.samsung.android.gallery.app.controller.mtp;

import O3.y;
import android.content.Intent;
import android.widget.Toast;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.mtp.MtpClient;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.blackboard.key.EventMessage;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MtpImportCmd extends BaseCommand {
    private long mSelectedCount;

    private String getDeviceName(MediaItem[] mediaItemArr) {
        String deviceNameFromPath;
        MtpClient instance = MtpClient.getInstance(getApplicationContext());
        for (MediaItem mediaItem : mediaItemArr) {
            if (mediaItem != null && mediaItem.getPath() != null && (deviceNameFromPath = instance.getDeviceNameFromPath(mediaItem.getPath())) != null) {
                return deviceNameFromPath;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void onSelectionCompleted(EventContext eventContext, ArrayList<Object> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            MediaItem[] mediaItemArr = (MediaItem[]) arrayList.get(0);
            if (mediaItemArr.length > 0) {
                operateImport(mediaItemArr);
            } else {
                Toast.makeText(getContext(), R.string.no_selection_items, 0).show();
            }
        }
    }

    private void operateImport(MediaItem[] mediaItemArr) {
        getBlackboard().publish("data://user/selected", mediaItemArr);
        Intent intent = new Intent();
        intent.setClassName("com.sec.android.gallery3d", "com.samsung.android.gallery.app.service.MtpImportService");
        intent.setAction("com.samsung.android.gallery.app.service.START_SERVICE");
        intent.putExtra("blackboard_name", getBlackboard().getName());
        intent.putExtra("mtp_device_name", getDeviceName(mediaItemArr));
        getContext().startService(intent);
    }

    public Long getAnalyticsValue() {
        return Long.valueOf(this.mSelectedCount);
    }

    public String getEventId() {
        return AnalyticsEventId.EVENT_MENU_IMPORT.toString();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (eventContext.isSelectionMode()) {
            ArrayList arrayList = new ArrayList();
            MediaItem[] selectedItems = eventContext.getSelectedItems();
            this.mSelectedCount = (long) selectedItems.length;
            arrayList.add(selectedItems);
            onSelectionCompleted(eventContext, arrayList);
            getBlackboard().postEvent(EventMessage.obtain(1003));
            return;
        }
        DataCollectionDelegate.getInitInstance(eventContext).setRequestData(new UriBuilder("data://user/selection/Item").appendArg("title", objArr[0]).build()).setOnDataCompleteListener(new y(4, this)).start();
    }
}
