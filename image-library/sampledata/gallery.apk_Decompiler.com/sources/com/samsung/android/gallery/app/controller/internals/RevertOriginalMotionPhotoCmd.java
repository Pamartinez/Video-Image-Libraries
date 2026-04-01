package com.samsung.android.gallery.app.controller.internals;

import O3.b;
import android.text.TextUtils;
import com.samsung.android.gallery.app.controller.DataCollectionDelegate;
import com.samsung.android.gallery.app.controller.EventContext;
import com.samsung.android.gallery.app.controller.internals.RevertOriginalImageCmd;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.nondestruction.NondestructiveEditor;
import com.samsung.android.gallery.support.library.sef.SefInfo;
import com.samsung.android.gallery.support.utils.AppResources;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.SefData;
import com.samsung.android.gallery.support.utils.UriBuilder;
import com.sec.android.gallery3d.R;
import java.util.ArrayList;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class RevertOriginalMotionPhotoCmd extends RevertOriginalImageCmd {
    private boolean isOriginStillImage(String str) {
        return !new SefData().read(str).contain(SefInfo.MOTION_PHOTO_DATA.keyName);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startConfirmDialog$0(MediaItem mediaItem, EventContext eventContext, ArrayList arrayList) {
        if (arrayList != null) {
            Log.d(this.TAG, "confirm {1}");
            executeInternal(mediaItem);
            return;
        }
        Log.d(this.TAG, "canceled");
        consumeExecuteListener(Boolean.TRUE);
    }

    private void startConfirmDialog(MediaItem mediaItem) {
        DataCollectionDelegate.getInitInstance(getHandler()).setRequestData(new UriBuilder("data://user/dialog/SimpleConfirm").appendArg("description", AppResources.getString(R.string.revert_motion_photo_as_still_image)).appendArg("option1", AppResources.getString(R.string.revert_to_original)).appendArg("screenId", getScreenId()).build()).setOnDataCompleteListener(new b(6, this, mediaItem)).start();
    }

    public void onExecute(EventContext eventContext, Object... objArr) {
        if (!(objArr == null || objArr.length == 0)) {
            MediaItem mediaItem = objArr[0];
            if (mediaItem instanceof MediaItem) {
                MediaItem mediaItem2 = mediaItem;
                long fileId = mediaItem2.getFileId();
                if (RevertOriginalImageCmd.ProcessManager.isProcessing(fileId)) {
                    Log.e((CharSequence) this.TAG, "onExecute skip. already under processing", Long.valueOf(fileId), Long.valueOf(RevertOriginalImageCmd.ProcessManager.get(fileId)));
                    return;
                }
                String hiddenOriginalFromPath = NondestructiveEditor.getHiddenOriginalFromPath(mediaItem2.getPath());
                if (TextUtils.isEmpty(hiddenOriginalFromPath) || isInvalidPath(mediaItem2.getPath(), hiddenOriginalFromPath)) {
                    Log.e(this.TAG, "revertMotionPhoto failed. invalid path");
                    return;
                } else if (!mediaItem2.isMotionPhoto() || !isOriginStillImage(hiddenOriginalFromPath)) {
                    executeInternal(mediaItem2);
                    return;
                } else {
                    startConfirmDialog(mediaItem2);
                    return;
                }
            }
        }
        String str = this.TAG;
        if (objArr == null) {
            objArr = new Object[]{"null"};
        }
        Log.e((CharSequence) str, "onExecute failed. wrong argument", objArr);
    }
}
