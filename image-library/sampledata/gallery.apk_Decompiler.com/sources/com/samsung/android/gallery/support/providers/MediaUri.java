package com.samsung.android.gallery.support.providers;

import android.net.Uri;
import com.samsung.android.gallery.support.config.DeviceConfig;
import com.samsung.android.gallery.support.config.SdkConfig;
import com.samsung.android.gallery.support.utils.FileUtils;
import com.samsung.android.gallery.support.utils.PocFeatures;
import i.C0212a;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class MediaUri {
    private static volatile UriInterface sInstance;

    private static UriInterface createInstance() {
        if (DeviceConfig.UNIT_TEST) {
            return new TestUri();
        }
        if (PocFeatures.isEnabled(PocFeatures.GmpAll)) {
            return new MediaUriGmp();
        }
        return getExternalInstance();
    }

    public static String getCameraPppUri() {
        return getInstance().getCameraPppUriString();
    }

    public static UriInterface getExternalInstance() {
        try {
            if (SdkConfig.atLeast(SdkConfig.SEM.U)) {
                return new MediaUriSecU();
            }
            if (SdkConfig.atLeast(SdkConfig.SEM.R)) {
                return new MediaUriSecR();
            }
            if (SdkConfig.atLeast(SdkConfig.SEM.Q)) {
                return new MediaUriSecQ();
            }
            if (SdkConfig.atLeast(SdkConfig.SEM.P)) {
                return new MediaUriSecP();
            }
            if (SdkConfig.atLeast(SdkConfig.SEM.O)) {
                return new MediaUriCmh();
            }
            return new MediaUriGed();
        } catch (Error | Exception e) {
            C0212a.y(e, new StringBuilder("init() failed e="), "MediaUri");
        }
    }

    public static Uri getFilesUri(String str) {
        String volumeName = FileUtils.getVolumeName(str);
        if ("external_primary".equals(volumeName)) {
            return getInstance().getFilesUri();
        }
        return getInstance().getFilesUri(volumeName);
    }

    public static UriInterface getInstance() {
        if (sInstance == null) {
            synchronized (MediaUri.class) {
                try {
                    if (sInstance == null) {
                        sInstance = createInstance();
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return sInstance;
    }

    public static Uri getSecMediaUri() {
        return getInstance().getSecMediaUri();
    }

    public static boolean isCameraUri(Uri uri) {
        if (uri == null || !getInstance().isCameraUri(uri.toString())) {
            return false;
        }
        return true;
    }

    public static boolean isFileContentUri(Uri uri) {
        if (uri == null) {
            return false;
        }
        String scheme = uri.getScheme();
        if ("file".equals(scheme) || "content".equals(scheme)) {
            return true;
        }
        return false;
    }

    public static boolean isFileUri(String str) {
        if (str == null || !str.startsWith("file://")) {
            return false;
        }
        return true;
    }

    public static boolean isSecMediaUri(Uri uri) {
        return uri != null && getInstance().isSecMediaUri(uri.toString());
    }

    public static Uri getSecMediaUri(boolean z) {
        return getInstance().getSecMediaUri(z);
    }

    public static boolean isSecMediaUri(String str) {
        return str != null && getInstance().isSecMediaUri(str);
    }

    public static boolean isFileContentUri(String str) {
        if (str != null) {
            return str.startsWith("content://") || str.startsWith("file://");
        }
        return false;
    }
}
