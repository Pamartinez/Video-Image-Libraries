package com.samsung.android.gallery.widget.livemotion.theme;

import com.samsung.android.gallery.module.data.MediaItem;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class ThemeKey {
    public static long getKey(MediaItem mediaItem) {
        long j2;
        if (mediaItem != null) {
            j2 = mediaItem.getFileId();
        } else {
            j2 = -1;
        }
        return Math.abs(j2);
    }
}
