package com.samsung.android.gallery.app.ui.viewer2.contentviewer.viewerobject;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.SuperHdrConfig;
import com.sec.android.gallery3d.R;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public class HdrDecorBgHandler extends AbsDecorBgHandler {
    private boolean isHdr() {
        MediaItem mediaItem = this.mModel.getMediaItem();
        if (mediaItem == null) {
            return false;
        }
        if (mediaItem.isImage()) {
            return isHdrImage(mediaItem);
        }
        if (mediaItem.isVideo()) {
            return isHdrVideo(mediaItem);
        }
        return false;
    }

    private boolean isHdrImage(MediaItem mediaItem) {
        if (SuperHdrConfig.isEnabled() && mediaItem != null) {
            if (mediaItem.hasPhotoHdrInfo()) {
                return true;
            }
            if (!mediaItem.isPhotoHdrSupported() || !mediaItem.isPhotoHdrCandidate()) {
                return false;
            }
            return true;
        }
        return false;
    }

    private boolean isHdrVideo(MediaItem mediaItem) {
        if (mediaItem == null || !mediaItem.isVideo() || !mediaItem.isHdrVideo()) {
            return false;
        }
        return true;
    }

    public void addActionInvokeListener() {
        super.addActionInvokeListener();
    }

    public int getChangedBgColor() {
        return R.color.play_button_view_hdr_bg_color;
    }

    public boolean supportChangeBg() {
        return isHdr();
    }
}
