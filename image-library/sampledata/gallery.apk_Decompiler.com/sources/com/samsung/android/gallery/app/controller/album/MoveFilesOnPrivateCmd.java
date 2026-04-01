package com.samsung.android.gallery.app.controller.album;

import Fb.h;
import H3.l;
import N2.j;
import com.samsung.android.gallery.app.controller.BaseCommand;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.module.cloud.CloudStateCompat;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.secured.KeepStorageManager;
import com.samsung.android.gallery.support.analytics.AnalyticsEventId;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.StorageInfo;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.io.File;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class MoveFilesOnPrivateCmd extends BaseCommand {
    MediaItem[] mItems;
    String mOperation;

    private String getMessage(int[] iArr) {
        int i2;
        if (iArr[0] == 0 && iArr[1] == 0) {
            return "";
        }
        if (CloudStateCompat.isNewGalleryAvailable()) {
            int i7 = iArr[0];
            if (i7 > 0 && iArr[1] > 0) {
                i2 = R.string.desc_remove_synced_items_samsung_cloud;
            } else if (i7 == 1) {
                i2 = R.string.desc_remove_synced_image_samsung_cloud;
            } else if (iArr[1] == 1) {
                i2 = R.string.desc_remove_synced_video_samsung_cloud;
            } else if (i7 > 1) {
                i2 = R.string.desc_remove_synced_images_samsung_cloud;
            } else {
                i2 = R.string.desc_remove_synced_videos_samsung_cloud;
            }
        } else {
            int i8 = iArr[0];
            if (i8 > 0 && iArr[1] > 0) {
                i2 = R.string.desc_remove_synced_items_onedrive;
            } else if (i8 == 1) {
                i2 = R.string.desc_remove_synced_image_onedrive;
            } else if (iArr[1] == 1) {
                i2 = R.string.desc_remove_synced_video_onedrive;
            } else if (i8 > 1) {
                i2 = R.string.desc_remove_synced_images_onedrive;
            } else {
                i2 = R.string.desc_remove_synced_videos_onedrive;
            }
        }
        return getContext().getString(i2);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$onConfirmed$0() {
        startService(this.mOperation, this.mItems);
    }

    private void startConfirmDialog() {
        int[] iArr = {0, 0};
        for (MediaItem mediaItem : this.mItems) {
            if (mediaItem.isCloud()) {
                if (iArr[0] > 0 && iArr[1] > 0) {
                    break;
                } else if (mediaItem.isImage()) {
                    iArr[0] = iArr[0] + 1;
                } else {
                    iArr[1] = iArr[1] + 1;
                }
            }
        }
        int length = this.mItems.length;
        DataCollectionDelegate.getInitInstance(getHandler()).setRequestData(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("type", 0).appendArg("title", getContext().getResources().getQuantityString(R.plurals.move_to_private_album_q, length, new Object[]{Integer.valueOf(length)})).appendArg("description", getMessage(iArr)).appendArg("option1", getContext().getString(R.string.move)).build()).setOnDataCompleteListener(new h(8, this)).start();
    }

    private void startService(String str, MediaItem[] mediaItemArr) {
        String str2;
        if ("move_to_secured".equals(str)) {
            str2 = KeepStorageManager.getInstance().getFilesPath();
        } else if ("move_from_secured".equals(str)) {
            str2 = StorageInfo.getDefault().root + File.separator + AppResources.getString(R.string.new_album_storage_internal_storage);
        } else {
            StringBuilder k = j.k("startService with wrong argument operation=", str, ", count=");
            k.append(mediaItemArr.length);
            throw new IllegalArgumentException(k.toString());
        }
        FileOpCmdHelper.getInstance().startService(getHandler(), mediaItemArr, str2, str, false, -1, false);
    }

    public String getEventId() {
        if ("move_to_secured".equals(this.mOperation)) {
            return AnalyticsEventId.EVENT_MOVE_TO_PRIVATE_ALBUM.toString();
        }
        return AnalyticsEventId.EVENT_MOVE_OUT_PRIVATE_ALBUM.toString();
    }

    public void onConfirmed(EventContext eventContext, ArrayList<Object> arrayList) {
        int i2;
        boolean z = false;
        if (arrayList == null || arrayList.isEmpty()) {
            i2 = 0;
        } else {
            i2 = ((Integer) arrayList.get(0)).intValue();
        }
        String str = this.TAG;
        StringBuilder sb2 = new StringBuilder("onConfirmed=");
        if (i2 == 1) {
            z = true;
        }
        sb2.append(z);
        Log.d(str, sb2.toString());
        if (i2 == 1) {
            SimpleThreadPool.getInstance().execute(new l(0, this));
        }
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        String str = objArr[0];
        this.mOperation = str;
        this.mItems = objArr[1];
        if ("move_to_secured".equals(str)) {
            startConfirmDialog();
        } else {
            startService(this.mOperation, this.mItems);
        }
    }
}
