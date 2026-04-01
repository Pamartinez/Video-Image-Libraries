package com.samsung.android.gallery.module.viewer;

import A.a;
import E3.c;
import N2.j;
import com.samsung.android.gallery.module.data.MediaItem;
import com.samsung.android.gallery.support.library.SeApiCompat;
import com.samsung.android.gallery.support.utils.GalleryPreference;
import com.samsung.android.gallery.support.utils.Log;
import com.samsung.android.gallery.support.utils.PreferenceName;
import com.samsung.android.gallery.support.utils.SimpleThreadPool;
import com.samsung.android.gallery.support.utils.UnsafeCast;
import com.samsung.android.sdk.globalpostprocmgr.GlobalPostProcInternalPPInterface;
import com.samsung.android.sum.core.types.NumericEnum;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;
import ld.b;

/* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
public abstract class QuickCropHelper {
    private static volatile HashSet<String> sSupportedFormats;

    /* compiled from: r8-map-id-85efad48717c48307252110572a40cba967e8b6d39747b0efe99f107082547d8 */
    public static class QuickCropInfo {
        String formats;
        int version;

        public QuickCropInfo(String str) {
            String str2;
            if (str != null) {
                try {
                    String[] split = str.split(NumericEnum.SEP);
                    this.version = split.length > 0 ? UnsafeCast.toInt(split[0]) : 0;
                    if (split.length > 1) {
                        str2 = split[1];
                    } else {
                        str2 = null;
                    }
                    this.formats = str2;
                } catch (Exception e) {
                    a.s(e, j.k("QuickCropInfo failed {", str, "} e="), "QuickCropHelper");
                }
            }
        }
    }

    private static HashSet<String> getMimeSet(String str) {
        if (str != null) {
            return (HashSet) Arrays.stream(str.split(GlobalPostProcInternalPPInterface.SPLIT_REGEX)).collect(Collectors.toCollection(new b(7)));
        }
        return new HashSet<>();
    }

    public static boolean isSupported() {
        return isSupported((Runnable) null);
    }

    public static boolean isSupportedFormat(MediaItem mediaItem) {
        if (mediaItem.isJpeg()) {
            if (!isSupported() || !sSupportedFormats.contains("image/jpeg")) {
                return false;
            }
            return true;
        } else if (mediaItem.isHeif()) {
            if (!isSupported() || !sSupportedFormats.contains("image/heic")) {
                return false;
            }
            return true;
        } else if (!mediaItem.isVideo() || mediaItem.isCloudOnly()) {
            return false;
        } else {
            return true;
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void lambda$isSupported$0(Runnable runnable, long j2) {
        String quickCropFormats = SeApiCompat.getQuickCropFormats();
        sSupportedFormats = getMimeSet(quickCropFormats);
        GalleryPreference instance = GalleryPreference.getInstance();
        PreferenceName preferenceName = PreferenceName.QUICK_CROP_FORMATS;
        instance.saveState(preferenceName, "" + SeApiCompat.version + ':' + quickCropFormats);
        if (runnable != null && sSupportedFormats.size() > 0) {
            Log.d("QuickCropHelper", "run callback");
            runnable.run();
        }
        Log.d("QuickCropHelper", "isSupported(SEP) {" + SeApiCompat.version + ':' + quickCropFormats + "} +" + (System.currentTimeMillis() - j2));
    }

    public static boolean isSupported(Runnable runnable) {
        if (sSupportedFormats == null) {
            String loadString = GalleryPreference.getInstance().loadString(PreferenceName.QUICK_CROP_FORMATS, (String) null);
            QuickCropInfo quickCropInfo = new QuickCropInfo(loadString);
            sSupportedFormats = getMimeSet(quickCropInfo.formats);
            Log.d("QuickCropHelper", "isSupported {" + loadString + ',' + sSupportedFormats.size() + '}');
            if (quickCropInfo.version != SeApiCompat.version) {
                SimpleThreadPool.getInstance().execute(new c(runnable, System.currentTimeMillis(), 5));
                return false;
            }
        }
        if (sSupportedFormats.size() > 0) {
            return true;
        }
        return false;
    }
}
