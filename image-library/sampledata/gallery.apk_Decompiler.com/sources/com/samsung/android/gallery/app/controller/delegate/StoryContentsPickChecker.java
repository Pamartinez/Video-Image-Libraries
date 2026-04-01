package com.samsung.android.gallery.app.controller.delegate;

import A4.C0370e;
import android.content.Context;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.blackboard.Blackboard;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.PreferenceFeatures;
import com.samsung.android.sdk.globalpostprocmgr.parameter.IFormat;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class StoryContentsPickChecker extends PickerSelectableChecker {
    public StoryContentsPickChecker(Blackboard blackboard) {
        super(blackboard);
    }

    private boolean checkIf360Content(MediaItem mediaItem) {
        if (!SdkConfig.lessThan(SdkConfig.SEM.T_MR5)) {
            return true;
        }
        if (mediaItem.isImage()) {
            if (!mediaItem.is360Image()) {
                return true;
            }
            return false;
        } else if (!mediaItem.is360VideoExtended()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean validImageFormat(MediaItem mediaItem) {
        if ((mediaItem.isJpeg() || mediaItem.isBmp() || mediaItem.isHeif()) && checkIf360Content(mediaItem)) {
            return true;
        }
        return false;
    }

    private boolean validImageSize(MediaItem mediaItem) {
        if (mediaItem.getHeight() * mediaItem.getWidth() > 200000000 || Math.min(mediaItem.getWidth(), mediaItem.getHeight()) < 480 || ((float) mediaItem.getWidth()) / ((float) mediaItem.getHeight()) < 0.33333334f || ((float) mediaItem.getWidth()) / ((float) mediaItem.getHeight()) > 3.0f) {
            return false;
        }
        return true;
    }

    private boolean validVideoFormat(MediaItem mediaItem) {
        if (mediaItem.isCloudOnly() || !checkIf360Content(mediaItem) || mediaItem.getMimeType() == null) {
            return false;
        }
        if (mediaItem.getMimeType().endsWith(IFormat.FORMAT_MP4) || mediaItem.getMimeType().endsWith("3gp")) {
            return true;
        }
        return false;
    }

    private boolean validVideoSize(MediaItem mediaItem) {
        if (mediaItem.getHeight() * mediaItem.getWidth() > 16777216 || ((long) mediaItem.getFileDuration()) >= 5400000 || mediaItem.getFileDuration() <= 0) {
            return false;
        }
        return true;
    }

    public int getUnSelectableStringId() {
        return R.string.unsupported_items_not_selected;
    }

    public boolean selectable(MediaItem mediaItem) {
        if (isSelectable(mediaItem)) {
            return true;
        }
        lazyFilterLog(1001, new C0370e(mediaItem, 2), 100);
        return false;
    }

    public boolean isSelectable(MediaItem mediaItem) {
        boolean selectable = super.selectable(mediaItem);
        if (!selectable || !PreferenceFeatures.OneUi5x.STORY_ONE_UI_50) {
            return selectable;
        }
        return mediaItem.isImage() ? validImageFormat(mediaItem) && validImageSize(mediaItem) : validVideoFormat(mediaItem) && validVideoSize(mediaItem);
    }

    public void showExceedMaxCountToast(Context context) {
    }
}
