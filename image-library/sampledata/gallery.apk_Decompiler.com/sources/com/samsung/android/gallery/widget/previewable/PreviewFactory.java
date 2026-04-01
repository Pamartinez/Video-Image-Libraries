package com.samsung.android.gallery.widget.previewable;

import com.samsung.android.gallery.database.dbtype.FileItemInterface;
import com.samsung.android.gallery.database.dbtype.StorageType;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.module.data.MediaItemUtil;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.widget.previewable.Previewable;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class PreviewFactory {
    public static Previewable createPreview(FileItemInterface fileItemInterface, Previewable.PreviewListener previewListener) {
        if (fileItemInterface == null) {
            return null;
        }
        if (fileItemInterface.isVideo()) {
            if (previewListener.isPlaybackPreview()) {
                return new PlaybackPreviewVideo(fileItemInterface.getPath(), previewListener);
            }
            return new PreviewVideo2((MediaItem) fileItemInterface, previewListener);
        } else if (fileItemInterface.isGif()) {
            return new PreviewGif(fileItemInterface.getPath(), previewListener);
        } else {
            return null;
        }
    }

    public static Previewable createStoryHighlightPreview(FileItemInterface fileItemInterface, Previewable.PreviewListener previewListener) {
        if (fileItemInterface == null || !fileItemInterface.isVideo()) {
            return null;
        }
        if (fileItemInterface.isHdr10Video()) {
            return new PreviewHdrVideo(fileItemInterface, previewListener);
        }
        return new PreviewVideo2((MediaItem) fileItemInterface, previewListener);
    }

    public static boolean isPreviewableFormat(FileItemInterface fileItemInterface) {
        if (fileItemInterface == null || !fileItemInterface.isVideo() || fileItemInterface.isBroken() || !supportVideoFormat(fileItemInterface) || !supportStorageType(fileItemInterface) || !supportedResolution(fileItemInterface) || !supportCloudOnly(fileItemInterface)) {
            return false;
        }
        return true;
    }

    private static boolean supportCloudOnly(FileItemInterface fileItemInterface) {
        if (!fileItemInterface.isCloudOnly() || MediaItemUtil.supportCloudPreviewPlay((MediaItem) fileItemInterface)) {
            return true;
        }
        return false;
    }

    private static boolean supportStorageType(FileItemInterface fileItemInterface) {
        if (fileItemInterface.getStorageType() != StorageType.RemoteItem) {
            return true;
        }
        return false;
    }

    private static boolean supportVideoFormat(FileItemInterface fileItemInterface) {
        if (SdkConfig.atLeast(SdkConfig.SEM.U_MR5)) {
            return true;
        }
        if (fileItemInterface.isHdr10Video() || fileItemInterface.isHlgVideo()) {
            return false;
        }
        return true;
    }

    private static boolean supportedResolution(FileItemInterface fileItemInterface) {
        int height = fileItemInterface.getHeight() * fileItemInterface.getWidth();
        if (height <= 0 || height > 16777216) {
            return false;
        }
        return true;
    }
}
