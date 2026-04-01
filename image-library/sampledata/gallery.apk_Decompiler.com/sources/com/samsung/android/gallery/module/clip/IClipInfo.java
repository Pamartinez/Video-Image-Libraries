package com.samsung.android.gallery.module.clip;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.RectF;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public interface IClipInfo {
    Bitmap getBitmap(boolean z) {
        return null;
    }

    Blackboard getBlackboard();

    String getContainerKey();

    Context getContext();

    int getCurrentFrame() {
        return -1;
    }

    RectF getDisplayRect();

    String getFilterText() {
        return null;
    }

    Bitmap getLowBitmap();

    MediaItem getMediaItem();

    int getPosition();

    Dialog getProgressDialog();

    int getTopMargin();

    boolean isCacheAvailable() {
        return false;
    }

    boolean isMotionPhotoVideoMode() {
        return false;
    }

    void requestCaptureProgress(boolean z) {
    }
}
