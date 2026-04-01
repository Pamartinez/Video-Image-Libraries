package com.samsung.android.gallery.module.story.transcode.util;

import android.os.Handler;
import com.samsung.android.gallery.support.utils.ThreadUtil;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class VideoHandler {
    public static Handler sHandler;

    public static Handler getHandler() {
        if (sHandler == null) {
            sHandler = new Handler(ThreadUtil.createBackgroundThreadLooper("VideoHandler"));
        }
        return sHandler;
    }
}
