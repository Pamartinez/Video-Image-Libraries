package com.samsung.android.gallery.app.ui.menu;

import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.utils.Features;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MenuSupportHelper {
    public static long getSupportForGroup(MediaItem[] mediaItemArr) {
        int length = mediaItemArr.length;
        if (length == 0) {
            return 0;
        }
        int i2 = 0;
        for (MediaItem mediaItem : mediaItemArr) {
            if (mediaItem != null && mediaItem.isFolder()) {
                i2++;
            }
        }
        if (length != 1 || i2 <= 0) {
            return 104;
        }
        return 112;
    }

    public static boolean supportCreate() {
        if (supportCreateImage() || supportCreateVideo()) {
            return true;
        }
        return false;
    }

    public static boolean supportCreateCollage() {
        return Features.isEnabled(Features.SUPPORT_CREATE_COLLAGE);
    }

    public static boolean supportCreateGif() {
        return !Features.isEnabled(Features.IS_GED);
    }

    public static boolean supportCreateHighlightReel() {
        return Features.isEnabled(Features.SUPPORT_CREATE_HIGHLIGHT_REEL);
    }

    public static boolean supportCreateImage() {
        if (supportCreateGif() || supportCreateCollage()) {
            return true;
        }
        return false;
    }

    public static boolean supportCreateMovie() {
        if (Features.isEnabled(Features.SUPPORT_CREATE_MOVIE) || Features.isEnabled(Features.SUPPORT_CREATE_MOVIE_V2) || Features.isEnabled(Features.SUPPORT_MULTI_VIDEO_EDIT)) {
            return true;
        }
        return false;
    }

    public static boolean supportCreateVideo() {
        if (supportCreateMovie() || supportCreateHighlightReel()) {
            return true;
        }
        return false;
    }
}
