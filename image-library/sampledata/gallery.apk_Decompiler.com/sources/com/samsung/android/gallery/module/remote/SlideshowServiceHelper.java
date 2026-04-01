package com.samsung.android.gallery.module.remote;

import android.content.Context;
import android.content.Intent;
import com.samsung.android.gallery.support.utils.Log;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SlideshowServiceHelper {
    private static boolean checkStop(String str) {
        if (str == null || !str.contains("GalleryActivity")) {
            return false;
        }
        return true;
    }

    private static String getServiceName() {
        return "com.samsung.android.gallery.app.remote.SlideshowServiceOnRemoteV2";
    }

    public static void startService(Context context, String str, String str2) {
        try {
            Intent intent = new Intent();
            intent.setClassName("com.sec.android.gallery3d", getServiceName());
            intent.setAction("com.samsung.android.gallery.app.service.SLIDESHOW_START_SERVICE");
            intent.putExtra("blackboard_name", str);
            intent.putExtra("location_key", str2);
            context.startService(intent);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }

    public static void stopService(Context context, String str) {
        if (checkStop(str)) {
            Log.rm("SlideshowServiceHelper", "Stop slideshow service using blackboard");
            stopSlideshowService(context);
        }
    }

    private static void stopSlideshowService(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.sec.android.gallery3d", getServiceName());
        intent.setAction("com.samsung.android.gallery.app.service.SLIDESHOW_STOP_SERVICE");
        context.stopService(intent);
    }
}
