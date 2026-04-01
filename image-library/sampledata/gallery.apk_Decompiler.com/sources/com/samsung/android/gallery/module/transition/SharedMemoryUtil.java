package com.samsung.android.gallery.module.transition;

import android.graphics.Bitmap;
import android.os.Bundle;
import com.samsung.android.gallery.module.graphics.BitmapUtils;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.Logger;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class SharedMemoryUtil {
    public static Bitmap fetchBitmap(Bundle bundle) {
        String string = bundle.getString("launch_from");
        if (string == null) {
            Log.at("SharedMemoryUtil", "fetchBitmap failed, launchedFrom is null.");
            return null;
        }
        long currentTimeMillis = System.currentTimeMillis();
        int i2 = bundle.getInt("bitmap_hash");
        Bitmap decodeUri = BitmapUtils.decodeUri(getSharedMemoryUri(string) + i2);
        StringBuilder u = C0212a.u("fetchBitmap {", string, GlobalPostProcInternalPPInterface.SPLIT_REGEX, i2, GlobalPostProcInternalPPInterface.SPLIT_REGEX);
        u.append(Logger.toSimpleString(decodeUri));
        u.append(" +");
        u.append(System.currentTimeMillis() - currentTimeMillis);
        Log.at("SharedMemoryUtil", u.toString());
        return decodeUri;
    }

    private static String getSharedMemoryUri(String str) {
        str.getClass();
        char c5 = 65535;
        switch (str.hashCode()) {
            case 13968997:
                if (str.equals("com.samsung.app.slowmotion")) {
                    c5 = 0;
                    break;
                }
                break;
            case 783420324:
                if (str.equals("com.sec.android.app.vepreload")) {
                    c5 = 1;
                    break;
                }
                break;
            case 1165734676:
                if (str.equals("com.samsung.app.newtrim")) {
                    c5 = 2;
                    break;
                }
                break;
            case 2117691547:
                if (str.equals("com.samsung.android.video")) {
                    c5 = 3;
                    break;
                }
                break;
        }
        switch (c5) {
            case 0:
                return "content://com.samsung.app.slowmotion.provider.SharedMemoryProvider/ashmem/";
            case 1:
                if (SdkConfig.atLeast(SdkConfig.SEM.S)) {
                    return "content://com.sec.android.app.vepreload.singleedit.provider.SharedMemoryProvider/ashmem/";
                }
                return "content://com.sec.android.app.vepreload.provider.SharedMemoryProvider/ashmem/";
            case 2:
                return "content://com.samsung.app.newtrim.data.provider.SharedMemoryProvider/ashmem/";
            case 3:
                return "content://com.samsung.android.video.player.provider.SharedMemoryProvider/ashmem/";
            default:
                return null;
        }
    }
}
